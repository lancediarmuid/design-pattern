> 代理、适配器、桥接、装饰，这四个模式有什么区别

## 适配器模式的原理及实现
### 定义：
- **适配器模式：** 这个模式是用来做适配的，它将不兼容的接口转换为可兼容的接口，让原本由于接口不兼容而不能一起工作的类可以一起工作。

### 实现的方式：
1. 类适配器：使用继承关系来实现；
2. 对象适配器：使用组合关系来实现；

```java
// 类适配器: 基于继承
public interface ITarget {
  void f1();
  void f2();
  void fc();
}

public class Adaptee {
  public void fa() { //... }
  public void fb() { //... }
  public void fc() { //... }
}

public class Adaptor extends Adaptee implements ITarget {
  public void f1() {
    super.fa();
  }
  
  public void f2() {
    //...重新实现f2()...
  }
  
  // 这里fc()不需要实现，直接继承自Adaptee，这是跟对象适配器最大的不同点
}

// 对象适配器：基于组合
public interface ITarget {
  void f1();
  void f2();
  void fc();
}

public class Adaptee {
  public void fa() { //... }
  public void fb() { //... }
  public void fc() { //... }
}

public class Adaptor implements ITarget {
  private Adaptee adaptee;
  
  public Adaptor(Adaptee adaptee) {
    this.adaptee = adaptee;
  }
  
  public void f1() {
    adaptee.fa(); //委托给Adaptee
  }
  
  public void f2() {
    //...重新实现f2()...
  }
  
  public void fc() {
    adaptee.fc();
  }
}
```

- **针对这两种实现方式，在实际的开发中的使用判断：**
  - 如果 Adaptee 接口并不多，那两种实现方式都可以；
  - 如果 Adaptee 接口很多，而且 Adaptee 和 ITarget 接口定义大部分都相同，推荐使用类适配器，因为 Adaptor 复用父类 Adaptee 的接口，比起对象适配器的实现方式，Adaptor 的代码量要少一些；
  - 如果 Adaptee 接口很多，而且 Adaptee 和 ITarget 接口定义大部分都不相同，推挤使用对象适配器，因为组合结构相对于继承更加灵活。

## 适配器模式应用场景
- **作用：**
  - 一般来说，适配器模式可以看作一种“补偿模式”，用来补救设计上的缺陷。应用这种模式算是“无奈之举”。如果在设计初期，我们就能协调规避接口不兼容的问题，那这种模式就没有应用的机会了。

### “接口不兼容”的场景：
1. **封装有缺陷的接口设计**
   1. 依赖的外部系统在接口设计方面有缺陷，如存在大量静态方法，引入后会影响到自身代码的可测试性。
   2. **为了隔离设计上的缺陷，我们希望对外部系统提供的接口进行二次封装，抽象出更好的接口设计** ，这时候就可以应用适配器模式了。

```java

public class CD { //这个类来自外部sdk，我们无权修改它的代码
  //...
  public static void staticFunction1() { //... }
  
  public void uglyNamingFunction2() { //... }

  public void tooManyParamsFunction3(int paramA, int paramB, ...) { //... }
  
   public void lowPerformanceFunction4() { //... }
}

// 使用适配器模式进行重构
public class ITarget {
  void function1();
  void function2();
  void fucntion3(ParamsWrapperDefinition paramsWrapper);
  void function4();
  //...
}
// 注意：适配器类的命名不一定非得末尾带Adaptor
public class CDAdaptor extends CD implements ITarget {
  //...
  public void function1() {
     super.staticFunction1();
  }
  
  public void function2() {
    super.uglyNamingFucntion2();
  }
  
  public void function3(ParamsWrapperDefinition paramsWrapper) {
     super.tooManyParamsFunction3(paramsWrapper.getParamA(), ...);
  }
  
  public void function4() {
    //...reimplement it...
  }
}
```

2. **统一多个类的接口设计**
   1. 系统中某个功能的实现依赖外部系统（或者外部类）。通过适配器模式，将它们的接口适配为统一的接口蒂尼，然后我们就可以使用多态的特性来复用代码逻辑。
   2. 业务功能场景：
      1. 假设我们的系统要对用户输入的文本内容做敏感词过滤，为了提高过滤的召回率，我们引入了多款第三方敏感词过滤系统，依次对用户输入的内容进行过滤，过滤掉尽可能多的敏感词。但是，，每个系统提供的过滤接口是不同的。这就意味着我们没法复用一套逻辑来调用各个系统。这个时候，我们就可以使用适配器模式，将所有系统的接口适配为统一的接口定义，这样我们可以调用敏感词过滤的代码。

```java
public class ASensitiveWordsFilter { // A敏感词过滤系统提供的接口
  //text是原始文本，函数输出用***替换敏感词之后的文本
  public String filterSexyWords(String text) {
    // ...
  }
  
  public String filterPoliticalWords(String text) {
    // ...
  } 
}

public class BSensitiveWordsFilter  { // B敏感词过滤系统提供的接口
  public String filter(String text) {
    //...
  }
}

public class CSensitiveWordsFilter { // C敏感词过滤系统提供的接口
  public String filter(String text, String mask) {
    //...
  }
}

// 未使用适配器模式之前的代码：代码的可测试性、扩展性不好
public class RiskManagement {
  private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
  private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
  private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();
  
  public String filterSensitiveWords(String text) {
    String maskedText = aFilter.filterSexyWords(text);
    maskedText = aFilter.filterPoliticalWords(maskedText);
    maskedText = bFilter.filter(maskedText);
    maskedText = cFilter.filter(maskedText, "***");
    return maskedText;
  }
}

// 使用适配器模式进行改造
public interface ISensitiveWordsFilter { // 统一接口定义
  String filter(String text);
}

public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
  private ASensitiveWordsFilter aFilter;
  public String filter(String text) {
    String maskedText = aFilter.filterSexyWords(text);
    maskedText = aFilter.filterPoliticalWords(maskedText);
    return maskedText;
  }
}
//...省略BSensitiveWordsFilterAdaptor、CSensitiveWordsFilterAdaptor...

// 扩展性更好，更加符合开闭原则，如果添加一个新的敏感词过滤系统，
// 这个类完全不需要改动；而且基于接口而非实现编程，代码的可测试性更好。
public class RiskManagement { 
  private List<ISensitiveWordsFilter> filters = new ArrayList<>();
 
  public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
    filters.add(filter);
  }
  
  public String filterSensitiveWords(String text) {
    String maskedText = text;
    for (ISensitiveWordsFilter filter : filters) {
      maskedText = filter.filter(maskedText);
    }
    return maskedText;
  }
}
```

3. **替换依赖的外部系统**
   - 当我们把项目中依赖的一个外部系统替换为另一个外部系统的时候，利用适配器模式

```java
// 外部系统A
public interface IA {
  //...
  void fa();
}
public class A implements IA {
  //...
  public void fa() { //... }
}
// 在我们的项目中，外部系统A的使用示例
public class Demo {
  private IA a;
  public Demo(IA a) {
    this.a = a;
  }
  //...
}
Demo d = new Demo(new A());

// 将外部系统A替换成外部系统B
public class BAdaptor implemnts IA {
  private B b;
  public BAdaptor(B b) {
    this.b= b;
  }
  public void fa() {
    //...
    b.fb();
  }
}
// 借助BAdaptor，Demo的代码中，调用IA接口的地方都无需改动，
// 只需要将BAdaptor如下注入到Demo即可。
Demo d = new Demo(new BAdaptor(new B()));
```

4. **兼容老版本接口**
   - 在做版本升级的时候，对于一些要废弃的接口，我们不直接将其删除，而是暂时保留，并且标注为 `deprecated` ，并将内部实现逻辑委托为新的接口实现。
   - 这样做让使用它的项目有个过渡期，而不是强制进行代码修改。

5. **适配不同格式的数据**
   - 适配器模式主要用于接口的适配，实际上，它还可以用在不同格式的数据之间的适配。
   - 比如，把从不同征信系统拉取的不同格式的征信数据，统一为相同的格式，以方便存储和使用。
   - Java 中 Arrays.asList() 也可以看作一种数据适配器，将数组类型的数据转化为集合容器类型。

## 适配器模式在 Java 日志中的应用
- Slf4j 日志框架，它相当于 JDBC 规范，提供了一套打印日志的统一接口规范。不过它只定义了接口，并没有提供具体的实现，需要配合其他日志框架（log4j等）来使用。
- Slf4j 的出现晚于 log4j 等日志框架，是最典型的适配器模式实践。
- 在开发义务系统或者开发框架、组件的时候，统一使用 Slf4j 提供的接口来编写打印日志的代码，具体使用哪种日志框架实现，是可以动态地指定，只需要将相应的 SDK 导入到项目即可。（**Java SPI 技术**）
- Slf4j 不仅仅提供了从其他日志框架到 Slf4j 的适配器，还提供了反向适配器，也就是从 Slf4j 到其他日志框架的适配。

## 代理、桥接、装饰器、适配器 4 中设计模式的区别：
- 代理、桥接、装饰器、适配器，是比较常用的结构型设计模式，代码结构非常类似。笼统来说，它们可以成为 Wrapper 模式，也就是通过 Wrapper 类二次封装原始类。

- **代理模式：** 代理模式在不改变原始类接口的条件下，为原始类定义一个代理类，主要目的是控制访问，而非加强功能，这是它跟装饰器模式最大的不同。
- **桥接模式：** 桥接模式的目的是将接口部分和实现部分分离，从而让它们可以较为容易、也相对独立地加以改变。
- **装饰器模式：** 装饰器模式在不改变原始类接口的情况下，对原始类功能进行增强，并且支持多个装饰器的嵌套使用。
- **适配器模式：** 适配器模式是一种事后的补救策略。适配器提供跟原始类不同的接口，而代理模式、装饰器模式提供的都是跟原始类相同的接口。