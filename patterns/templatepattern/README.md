## 模板模式的原理与实现
### 定义：
- 模板方法模式在一个方法中定义一个算法股价，并将某些步骤推迟到子类中实现。模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤。

这里的“算法”，我们可以理解为广义上的“业务逻辑”，并补特指数据结构和算法中的“算法”。这里的算法骨架就是“模板”，包含算法骨架的方法就是“模板方法”，这也是模板方法模式名字的由来。

### 实现原理：
```java
public abstract class AbstractClass {
  public final void templateMethod() {
    //...
    method1();
    //...
    method2();
    //...
  }
  
  protected abstract void method1();
  protected abstract void method2();
}

public class ConcreteClass1 extends AbstractClass {
  @Override
  protected void method1() {
    //...
  }
  
  @Override
  protected void method2() {
    //...
  }
}

public class ConcreteClass2 extends AbstractClass {
  @Override
  protected void method1() {
    //...
  }
  
  @Override
  protected void method2() {
    //...
  }
}

AbstractClass demo = ConcreteClass1();
demo.templateMethod();
```

## 模板方法的作用
### 复用
所有的子类可以复用父类中提供的模板方法的代码。

### 扩展
框架通过模板模式提供功能扩展点，让框架用户可以在不修改框架源码的情况下，基于扩展点定制化框架的功能

## 回调
### 回调的定义
A 类事先注册某个函数 F 到 B 类，A 类在调用 B 类的 P 函数的时候，B 类反过来调用 A 类注册给它的 F 函数。这里的 F 函数就是“回调函数”。**A 调用 B，B 反过来又调用 A，这种调用机制就叫作“回调”。**

### 回调原理解析
#### Java 关于回调的实现方式
- 使用包裹了回调函数的类对象
```java
public interface ICallback {
  void methodToCallback();
}

public class BClass {
  public void process(ICallback callback) {
    //...
    callback.methodToCallback();
    //...
  }
}

public class AClass {
  public static void main(String[] args) {
    BClass b = new BClass();
    b.process(new ICallback() { //回调对象
      @Override
      public void methodToCallback() {
        System.out.println("Call back me.");
      }
    });
  }
}
```

- 如果 ICallback、BClass 类是框架代码，AClass 是是哟个框架的客户端代码，我们可以通过 ICallback 定制 process() 函数，也就是说，框架因此具有了扩展能力。

### 应用场景
#### JdbcTemplate
Spring 提供了很多 Template 类，比如，JdbcTemplate、RedisTemplate、RestTemplate。尽管都叫作 xxxTemplate，**但它们并非基于模板模式来实现的，而是基于回调来实现的，确切地说应该是同步回调。而同步回调从应用场景上很像模板模式**，所以，在命名上，这些类使用 Template（模板）这个单词作为后缀。
- 在使用 JdbcTemplate 查询数据库信息的时候，一般会去实现 RowMapper<> 这个接口，然后重写方法 mapRow 方法，这里的 `mapRow` 就是回调方法。
- JdbcTemplate 通过回调的机制，将不变的执行流程抽离处理啊，放到模板方法 execute() 中，将可变的部分设计成回调 StatementCallback，由用户来定制。query() 函数是对 execute() 函数的二次封装，让接口用起来更加方便。

#### setClickListener()
在前端开发中，我们经常给控件事件监听器。比如 Button 的 click 事件

事件监听器很像回调，即传递一个包含回调函数(onClick())的对象给另一个函数。从应用场景上来看，它很像观察者模式，**即事件注册观察者(OnClickListener)** ，当用户点击按钮的时候，发送点击事件给观察者，并且执行相应的 onClick() 函数。

回调分为同步回调和异步回调。这里的 onClick 的回调算是异步回调，我们往 setOnClickListener() 函数中注册好回调函数之后，并不需要等待回调函数执行。

#### addShutdownHook()
- **Hook 和 Callback 的区别**
  - Callback 更侧重语法机制的描述；
  - Hook 更加侧重应用场景的描述。

- Hook 的方式算是一种异步回调。

## 模板模式 VS 回调
### 从代码实现上的区别：
- **回调** 
  - 基于组合关系来实现，把一个对象传递给另一个对象，是一种对象之间的关系；
- **模板模式**
  - 基于继承关系来实现，子类重写父类的抽象方法，是一种类之间的关系。

### 回调相对与模板模式的优势：
1. 像 Java 这种支支持单继承的语言，基于模板模式编写的子类，已经继承了一个父类，不再具有继承的能力。
2. 回调可以使用匿名类来创建回调对象，可以不用事先定义类；而模板方式针对不同的实现都要定义不同的子类。
3. 如果某个类中定义了多个模板方法，每个方法都有对应的抽象方法，那即便我们只用到其中一个模板方法，子类也必须实现所有的抽象方法。而回调就更加灵活，我们只需要往用到的模板方法中注入回调对象即可。