## 面向对象编程的优势
1. 更加能够应对大规模复杂程序的开发
2. 更易复用、易扩展、易维护
3. 更加人性化、更加高级、更加智能

## 反OO编程：
1. 滥用getter、setter方法
2. 滥用全局变量和全局方法
   1. 集中存放静态常量到一个类中，存在的问题：
      1. 影响代码的可维护性，提高了提交代码冲突的概率；
      2. 增加代码的编译时间，影响开发效率；
      3. 影响代码的复用性
3. 定义数据和方法分离的类
   1. 日常常用的 MVC web 开发模式称之为：基于贫血模型的开发模式
   2. 什么是贫血模型：
      1. 贫血模型（Anemic Domain Model由
MatinFowler提出）又称为失血模型，是指domain object仅有属性的getter/setter方法的纯数据类，将所有类的行为放到service层。原文他是这么说的“By pulling all the behavior out into services, however, you essentially end up with Transaction Scripts, and thus lose the advantages that the domain model can bring. ”他的原文我放上来了，英文好的同学可以看看：https://martinfowler.com/bliki/AnemicDomainModel.html 。 我觉得他有点学者气太重，这篇博客他都不知道为啥贫血模型会流行（I don't know why this anti-pattern is so common）。

## 抽象类跟接口的区别是什么
1. 抽象类的特征：
   1. 抽象类不允许被实例化，只能被继承；
   2. 抽象类可以包含属性和方法；
   3. 子类继承抽象类，必须实现抽象类中的所有抽象方法。
2. 接口类的特征：
   1. 接口不能包含属性；
   2. 接口只能声明方法，方法不能包含代码实现；
   3. 类实现接口的时候，必须实现接口中声明的所有方法；
3. 抽象类解决的问题：
   1. 抽象类更侧重于解决代码复用性的问题；
   2. 接口类更侧重与解决代码解耦，提高代码的可扩展性；
4. 使用场景的区别：
   1. 如果要表示一种is-a的关系的时候，使用抽象类，解决代码复用性问题；
   2. 如果要表示一种has-a的关系的时候，使用接口，解决抽象问题；
## 设计原则实践
