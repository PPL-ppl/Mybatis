1: mybatis配置  pom扫描main的xml文件
2：实体
3：Repository
4: RepositoryMapper.xml
5: 配置文件中注册

Test 原生接口实现
Test2 框架实现
Test3 查询一个学生ID 查询出学生信息 班级信息
Test4 查询一个班级ID 查询出班级信息 所有学生信息
Test5 多对多查询
Test6 二级缓存
Test7 动态sql
where能判断是否要删除SQL语句中的and关键字 where后面加and and会被删除
if判断是否将判断语句放进去
        <where>
             <if test="id!=0">
                 id=#{id}
             </if>
             <if test="username!=null">
                 and username=#{username}
             </if>
             <if test="password!=null">
                 and username=#{password}
             </if>
             <if test="age!=0">
                 and age=#{age}
             </if>
        </where>
choose when组合条件语句  从第一个when开始判断 如果能添加进去 就使用这个条件查询  这种方式的查询where条件最多只有一个
         <where>
             <choose>
                 <when test="id!=0">
                     id=#{id}
                 </when>
                 <when test="username!=null">
                     username=#{username}
                 </when>
                 <when test="password!=null">
                     password=#{password}
                 </when>
                 <when test="age!=0">
                     age=#{age}
                 </when>
             </choose>
         </where>
trim标签中的prefix和suffix属性会被用于生成实际的SQL语句 会于标签内部的语句拼接
如果语句前后出现prefixOverrides和suffixOverrides属性中的值，MyBatis会自动将其删除。
        select * from t_account
        <trim prefix="where" prefixOverrides="and">
            <if test="id!=0">
                id=#{id}
            </if>
            <if test="username!=null">
                and username=#{username}
            </if>
            <if test="password!=null">
                and password=#{password}
            </if>
            <if test="age!=0">
                and age=#{age}
            </if>
        </trim>
set标签
用于update
        update t_account
        <set>
            <if test="username!=null">
                username=#{username}
            </if>
            <if test="password!=null">
                password=#{password}
            </if>
            <if test="age!=0">
                age=#{age}
            </if>
        </set> where id=#{id}
foreach标签
可以迭代生成一系列的值
1 实体类中添加private List<Long> ids;
2

延迟加载  例子：Student.xml里
特定情况访问特定数据库 在其他情况下不访问某些表
例如：查询学生和班级时 学生和班级是两张不同的表 如果当前需求只需要学生信息，
     那么查询学生 单表就行 如果通过学生获取对应的班级信息，那必须查询两张表
     不同业务需求，需要查询不同的表 根据具体业务需求来动态减少数据表查询的工作 即延迟加载
在config.xml开启延迟加载

缓存： Test6
减少和数据库的交互次数 提升运行效率 例如 查询id=1 第一次查询后将对象保存到缓存中 下次查询之间在缓存中找
缓存分类
一级缓存
    SQLsSession级别 默认开启 并且不能关闭
    操作数据库需要创建sqlSession对象 在一个对象中有一个hashmap用于存储缓存数据
    不同sqlSession之间缓存数据区域互不影响
    作用域 sqlSession范围当一个sqlSession中执行两次相同sql语句时 第一次的结果保存在缓存中 第二次直接从缓存中取
    sqlSession执行了DML（insert update delete）时 mybatis必须清空缓存保持数据的准确性
二级缓存
    mapper级别的 默认关闭 可以开启
    使用二级缓存时 多个sqlSession使用同一个mapper的sql语句操作数据库，得到的数据会存在二级缓存
    同样是hashmap进行数据存储 相较于一级缓存 二级缓存的范围更大 多个sqlSession可以共用二级缓存
    二级缓存是跨sqlSession的
    作用域:Mapper的同一个namespace，不同的sqlSession两次执行相同的namespace下的SQL语句，参数也相等
    第一次成功后保存到二级缓存 第二次直接在二级缓存中取
    实现
    自带方式实现   例子Account
    1:  config.xml配置开启二级缓存
        <!--开启二级缓存-->
        <setting name="cacheEnabled" value="true"/>
    2:  在mapper.xml中配置
    3:  实体类实现序列化接口
    第三方实现
    1   添加依赖
                    <!--mybatis与ehcache整合-->
                   <dependency>
                       <groupId>org.mybatis.caches</groupId>
                       <artifactId>mybatis-ehcache</artifactId>
                       <version>1.1.0</version>
                   </dependency>

                   <!--ehcache依赖-->
                   <dependency>
                       <groupId>net.sf.ehcache</groupId>
                       <artifactId>ehcache</artifactId>
                       <version>2.10.0</version>
                   </dependency>
    2   添加配置文件 ehcache.xml
        <?xml version="1.0" encoding="UTF-8"?>
        <ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:noNamespaceSchemaLocation="../config/ehcache.xsd">
            <defaultCache
                    maxEntriesLocalHeap="10000"
                    eternal="false"
                    timeToIdleSeconds="120"
                    timeToLiveSeconds="120"
                    maxEntriesLocalDisk="10000000"
                    diskExpiryThreadIntervalSeconds="120"
                    memoryStoreEvictionPolicy="LRU">
            </defaultCache>
        </ehcache>
    3   config.xml配置开启二级缓存
        <!--开启二级缓存-->
        <setting name="cacheEnabled" value="true"/>
    4   Mapper.xml中配置二级缓存

    5   实体类不需要实现序列化接口
动态sql
