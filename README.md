# json-parser
* auth: wujiuye 2021/06/08
* 从2.x版本开始，hotkit-json更名为json-parser，并从hotkit项目中独立出来成为独立维护的项目。
* 建议使用最新版本：[json-parser-core](https://maven-badges.herokuapp.com/maven-central/com.github.wujiuye/json-parser-core)

[![Maven central](https://maven-badges.herokuapp.com/maven-central/com.github.wujiuye/json-parser-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wujiuye/json-parser-core)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

## 模块说明

### json-parser-core
`json`适配器组件，让切换`json`解析框架只需要切换依赖包即可。

### json-parser-adapter
json-parser-adapter是`json-parser-core`适配`springboot`的`starter`包，自动将`webmvc`或`webflux`的`json`解析工作交给`json-parser-core`完成，
以此让整个项目使用同一套`json`解析配置。

## json-parser-core

json-parser-core目前已适配jackson、gson，json-parser-core根据项目中导入了哪个json解析工具就使用哪个json解析框架。

* 注意：如果项目中即导入了jackson又导入了gson，那么将会按适配代码的执行顺序优先选择，见JsonUtils类，当然，也可通过配置方式切换（见配置说明）。

### 使用说明
1、在项目中添加json-parser-core依赖
```xml
<dependency>
    <groupId>com.github.wujiuye</groupId>
    <artifactId>json-parser-core</artifactId>
    <version>{version}</version>
</dependency>
```
2、在项目中添加jackson或者gson的依赖
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>{version}</version>
</dependency>
```
### 支持的API
目前支持的API见JsonParser接口。可扩展，欢迎提交合并请求。
```java
public interface JsonParser {
    // ======== 序列化 ==============
    <T> String toJsonString(T obj);
    // ======== 反序列化 ============
    <T> T fromJson(String jsonStr, Class<T> tClass);
    <T> T fromJson(InputStream jsonIn, Class<T> tClass);
    <T> T fromJson(String jsonStr, Type type);
    <T> T fromJson(InputStream jsonIn, Type type);
    <T> List<T> fromJsonArray(String jsonStr, TypeReference<List<T>> typeReference);
    <T> List<T> fromJsonArray(InputStream jsonIn, TypeReference<List<T>> typeReference);
    <K, V> Map<K, V> fromJsonMap(String jsonStr, TypeReference<Map<K, V>> typeReference);
    <K, V> Map<K, V> fromJsonMap(InputStream jsonIn, TypeReference<Map<K, V>> typeReference);
}
```

### 支持的配置项
```java
public class SerializeConfig {
    /**
     * 是否序列化null字段
     */
    private boolean serializeNulls = false;
    /**
     * 是否开启防xss攻击过滤
     */
    private boolean openXssFilter = true;
    /**
     * 使用的时区
     */
    private int timezone = 8;
    /**
     * Date类型序列化格式，配置为null则序列化为时间戳（反序列化时也会自动适配时间戳）
     */
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";
    /**
     * LocalDateTime类型序列化格式，配置为null则序列化为时间戳（反序列化时也会自动适配时间戳）
     */
    private String localDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    /**
     * LocalDate类型序列化格式，配置为null则序列化为时间戳（反序列化时也会自动适配时间戳）
     */
    private String localDateFormat = "yyyy-MM-dd";
}
```
修改配置：
```java
class Main{
    public static void main(String[] args){
        com.wujiuye.jsonparser.core.JsonUtils.resetSerializeConfig(new SerializeConfig());
    }
}
```

## json-parser-adapter

`json-parser-adapter`是`json-parser-core`适配`springboot`的`starter`包，自动将`webmvc`或`webflux`的`json`解析工作交给`json-parser-adapter`完成，以此让整个项目使用同一套`json`解析配置。

### 使用说明
1、在项目中添加json-parser-core依赖
```xml
<dependency>
    <groupId>com.github.wujiuye</groupId>
    <artifactId>json-parser-core</artifactId>
    <version>{version}</version>
</dependency>
```
2、在项目中添加json-parser-adapter依赖
```xml
<dependency>
    <groupId>com.github.wujiuye</groupId>
    <artifactId>json-parser-adapter</artifactId>
    <version>{version}</version>
</dependency>
```
3、在application.yaml中配置序列化&反序列化参数
```yaml
spring:
  json-parser:
    # 是否序列化null值，默认false
    serialize-null: false
    # Date类型序列化格式，默认：yyyy-MM-dd HH:mm:ss (如果配置为null，则会序列化为时间戳，反序列化则自动适配)
    date-format: yyyy-MM-dd HH:mm:ss
    # LocalDateTime类型序列化格式，默认：yyyy-MM-dd (如果配置为null，则会序列化为时间戳，反序列化则自动适配)
    local-date-format: yyyy-MM-dd
    # LocalDate类型序列化格式，默认：yyyy-MM-dd HH:mm:ss (如果配置为null，则会序列化为时间戳，反序列化则自动适配)
    local-date-time-format: yyyy-MM-dd HH:mm:ss
    # 时区（适配时间戳序列化&反序列化），默认为东8区
    timezone: 8
    # 是否开启xss过滤器，默认开启
    open-xss-filter: true
```
