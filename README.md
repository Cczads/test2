某电商业务场景1:
1.
（1）确定城市
如果是pc浏览器端：
对于未登录客户，可以获取到请求的ip地址，然后根据ip地址库查询出ip地址属于哪个城市。
其中根据ip地址库可以使用第三方服务，如阿里巴巴等提供了这样的接口查询服务。这种服务一般是收费服务，
所以我们要尽可能减少调用次数，那么我们可以使用redis做缓存，从阿里巴巴返回的结果在redis存一份，储存时间可根据需求设置，
当下次再有请求的话，先看redis有没有，有的话直接返回，没有的话才查询阿里巴巴服务。
对于已登录用户：
如果用户有设置过收货地址，那么根据默认收货地址来确定属于哪个城市。
如果是手机app，可以由手机客户端调用手机的定位功能确认出城市，然后将城市随着请求一起发过来。
（2）排序
查询目标城市是否有优先级表，没有的以99为最低排序的第一个字段，有的以该优先级表的排序字段为第一排序字段（为多个优先表排序），
如果该排序字段相同，可以根据分类表的排序字段为第二排序字段（为第一字段相同时排序，优先表排序相同，
或没有优先表时的99最低排序值时）
 设计并新建两表：
create table cy_city(
     id int not null primary key auto_increment,
     city varchar(16) not null comment '城市名'
)comment '城市表';
create table cy_city_priority(
    id int not null primary key auto_increment,
    city_id int not null comment '城市id',
   sort smallint(5) unsigned NOT NULL DEFAULT '99' 			 COMMENT '排序字段',
    cate_id int not null comment '分类id'
)comment '城市优先表';
查询sql语句：
select cg.id,
           cg.goods_name,
           cg.cate_id,
           cg.price,
           cg.original,
           cg.tags,
           cg.content,
           cg.summary,
           cg.is_sale
from cy_categories as ca
inner join cy_goods as cg on ca.id = cg.cate_id
left join cy_city_priority ccp on ccp.city_id = #{cityId} and ca.id = ccp.cate_id
order by case when ccp.id is null then 99 else ccp.sort end ,ca.sort
可以更进一步，每个城市和每个目录都必须有一条记录存在于 城市优先目录表里面，这样就直接 inner join，而且直接用sort字段，
不用case when 。那需要多付出的就是，当添加城市或者添加目录的时候，也要维护下这张 城市优先表

    本方案中存在的问题：
调用第三方服务时的性能不稳定（通过IP查到城市名），可能会影响我们的服务，可以在缓存中查不到城市时，
不进行调用方法，当默认城市进行处理，将该IP推到一个队列中，提供一个新线程来消费该队列，调用第三方服务进行查询，将结果存入redis中

2.
思路是，当请求来到时，小部分请求会转发到该服务，不影响大部分请求转发到原来的服务。可以提供一张表记录该新服务测试的用户id表，
可以存在redis的set集合中，请求来到时，在redis中查询该表的数据看否有该信息，来分开要走的功能
可以有两种方式：
（1）针对非微服务项目，在一个项目里面同时提供两种业务实现。	在controller层判断，上述的redis中查出用户id是否存在该值，
存在，调用新的排序方法，不存在就调用旧方法。
（2）针对微服务项目
微服务注册到注册中心的时候， 携带特殊标记字段，来区分灰度服务和旧服务。旧节点提供旧实现，灰度节点只提供新实现，
通过自定义负载均衡来将选定用户的请求转发给灰度服。



