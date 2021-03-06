## 什么是 DRY 原则
- 定义：Don't Repeat Yourself. 不要写重复的代码。

- 典型的代码重复场景：
  1. 实现逻辑重复；
  2. 功能语义重复
  3. 代码执行重复

### 实现逻辑重复
- 尽管代码的实现逻辑是相同的，但语义不同，我们判定它并不违反 DRY 原则。
- 对于包含重复代码的问题，我们可以通过抽象成更细粒度函数的方式来解决。

### 功能语义重复
- 两个函数命名不同，实现逻辑不同，但是功能是相同的，这样是违反了 DRY 原则。
- 我们应该在项目中，统一一种实现思路，都统一调用同一个函数。
- 如果不统一实现思路，有些地方调用这个函数，有些地方调用另外一个函数，会导致代码看起来很奇怪，给不熟悉这部分代码的同事增加了阅读的难度。需求变更的时候，也会导致一些莫名其妙的BUG。

### 代码执行重复
- 代码重复执行，可能会导致重复查询数据库I/O操作，数据库I/O是比较耗时的，在平时写代码的时候也应当减少此类I/O操作。

## 代码复用性
### 代码的复用性定义：
1. 代码复用表示一种行为：在开发新功能的时候，尽量复用已存在的代码。
2. 代码的可复用行表示一段代码可被复用的特性或能力：我们在编写代码的时候，让代码尽量可复用。
3. DRY 原则是一条原则：不要写重复的代码。

### 如何提高代码的复用性：
1. 减少代码耦合
2. 满足单一职责原则
3. 模块化
4. 业务与非业务逻辑分离
5. 通用代码下沉
6. 使用继承、多态、抽象、封装
7. 应用模板等设计模式

## 除此之外
### Rule of Three 原则
- 第一次编写的时候，我们不考虑复用性；
- 第二次遇到复用场景的时候，再进行重构使其复用。
- 需要注意的是，“Rule of Three”中的“Three”并不是真的就指确切的“三”，这里就是指“二”。

**我们可以不写可复用的代码，但一定不不能写重复的代码。**