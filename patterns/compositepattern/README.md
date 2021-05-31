> 组合模式，*不那么常用*

组合模式跟在面向对象设计中的“组合关系”，完全是两码事。这里讲得“组合模式”，主要是用来处理树形接口数据。这里的“数据”，可以简单理解为一组对象集合。

## 组合模式的原理与实现
### 门面模式的定义：
> Compose objects into tree structure to represent part-whole hierarchies.Composite lets client treat individual objects and compositions of objects uniformly.
- 将一组对象组织（Compose）成树形结构，以表示一种“部分 - 整体”的层次结构。组合让客户端可以统一单个对象和组合对象的处理逻辑。