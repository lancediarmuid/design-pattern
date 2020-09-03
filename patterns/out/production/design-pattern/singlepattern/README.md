## 使用单例模式的好处
- **定义：** 一个类只允许创建一个对象（或者实例），那这个类就是一个单例类，这种设计模式就叫做单例设计模式，简称单例模式。

### 解决资源竞争问题
1. 相对于分布式锁、并发队列的实现方式来说，单例模式的解决思相对简单很多；
2. 不用创建那么多 Logger 对象，节约了内存空间，也节省了文件句柄；

### 表示全局唯一类
- 在业务上，如果有些数据在系统中至应保存一份，那就比较适合设计为单例类。
- 如，配置信息类。在系统中，我们只有一个配置文件，当配置文件被加载到内存之后，以对象的形式存在，也理所应到只有一份。
- 再比如，唯一递增 ID 发号器，如果系统中存在两个对象，那就回生成重复 ID 的情况，所以，应该将 ID 发号器类设计成单例。

## 实现单例的方式
- 实现单例模式的关注点：
  1. 构造函数需要时 private 访问权限的，这样才能避免外部通过 new 创建实例；
  2. 考虑对象创建时的线程安全问题；
  3. 考虑是否支持延迟加载；
  4. 考虑 getInstance() 性能是否高（是否加锁）；

### 饿汉式
- 在类加载的时候，instalce 静态示例就已经创建并初始化好了，所以，instance 实例的创建过程是线程安全的。但是这样的方式不支持延迟加载。

```java
public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private static final IdGenerator instance = new IdGenerator();
  private IdGenerator() {}
  public static IdGenerator getInstance() {
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}
```

### 懒汉式
- 懒汉式相对于饿汉式的又是是支持延迟加载。

```java
public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private static IdGenerator instance;
  private IdGenerator() {}
  public static synchronized IdGenerator getInstance() {
    if (instance == null) {
      instance = new IdGenerator();
    }
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}
```

- 懒汉式的缺点，我们给 getInstance() 这个方法加了一把大锁(synchronzed)，导致这个函数的并发度很低。量化一下的话，并发度是1，也就是相当于串行操作了。如果这个单例类偶尔会被用到，那这种方式还可以接受。但是，如果频繁地用到，那频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈，这种实现方式就不可取了。

### 双重检测
- 饿汉式不支持延迟加载，懒汉式有性能问题，不支持高并发。双重检测实现方式是，既支持延迟加载、又支持高并发的单例实现方式。
- 这种实现方式中，只要 instance 被创建之后，即便再调用 getInstance() 函数也不会再进入到加锁逻辑中了。所以，这种实现方式解决了懒汉式的并发度低的问题。

```java
public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private static IdGenerator instance;
  private IdGenerator() {}
  public static IdGenerator getInstance() {
    if (instance == null) {
      synchronized(IdGenerator.class) { // 此处为类级别的锁
        if (instance == null) {
          instance = new IdGenerator();
        }
      }
    }
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}
```

### 静态内部类
- 利用 Java 的静态内部类。有点类似饿汉式，但做到了延迟加载。

```java
public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private IdGenerator() {}

  private static class SingletonHolder{
    private static final IdGenerator instance = new IdGenerator();
  }
  
  public static IdGenerator getInstance() {
    return SingletonHolder.instance;
  }
 
  public long getId() { 
    return id.incrementAndGet();
  }
}
/**
 * SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，
 * 并不会创建 SingletonHolder 实例对象。只有当调用 getInstance() 方法时，
 * SingletonHolder 才会被加载，这个时候才会创建 instance。instance 的唯一
 * 性、创建过程的线程安全性，都由 JVM 来保证。所以，这种实现方法既保证了线程
 * 安全，又能做到延迟加载。
 */
```

### 枚举
- 还有一种最简单的实现方式，就是基于枚举类型的单例实现。
- 这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。

```java
public enum IdGenerator {
  INSTANCE;
  private AtomicLong id = new AtomicLong(0);
 
  public long getId() { 
    return id.incrementAndGet();
  }
}
```

## 使用单例模式会带来的问题
### 单例对 OOP 特性的支持不友好

```java
public class Order {
  public void create(...) {
    //...
    long id = IdGenerator.getInstance().getId();
    //...
  }
}

public class User {
  public void create(...) {
    // ...
    long id = IdGenerator.getInstance().getId();
    //...
  }
}
```

- IdGenerator 的使用方式违背了基于接口而非实现的设计原则，也就违背了广义上理解的 OOP 的抽象特性。如果 未来某一天，我们希望针对不同的业务采用不同的 ID 生成算法。订单用订单的 ID 生成器，用户用用户的 ID 生成器。 **为了应对这个需求变化，我们需要修改所有用到的 IdGenerator 类的地方** ，这样的代码的改动就会比较大。

```java
public class Order {
  public void create(...) {
    //...
    long id = IdGenerator.getInstance().getId();
    // 需要将上面一行代码，替换为下面一行代码
    long id = OrderIdGenerator.getIntance().getId();
    //...
  }
}

public class User {
  public void create(...) {
    // ...
    long id = IdGenerator.getInstance().getId();
    // 需要将上面一行代码，替换为下面一行代码
    long id = UserIdGenerator.getIntance().getId();
  }
}
```

- 此外，单例对继承、多态特性的支持也不友好。单例类也可以被继承、也可以实现多态，只是实现起来会非常奇怪，会导致代码的可读性变差。不明白设计意图的人，看到这样的设计，会觉得莫名其妙。
- **所以，一旦选择将某个类设计成单例类，也就意味着放弃了继承和多态这两个强有力的面向对象特性，也就相当于损失了可以应对未来需求变化的扩展性。**

### 单例会隐藏类之间的依赖关系（降低了代码的可读性）
- 代码的可读性非常中要。阅读代码时，希望一眼能看出类与类之间的依赖关系，搞清楚这个类依赖了哪些外部类。
- 通过构造函数、参数传递等方式声明的类之间的依赖关系，我们通过查看函数的定义，就能很容易识别出来。但是单例类不用，使用时直接在函数中调用。
- 如果代码比较复杂的话，这种调用关系就会非常隐蔽。在阅读代码的时候，我们就需要仔细查看每个函数的代码实现，才能知道这个类到底依赖了哪些单例类。

### 单例对代码的扩展性不友好
- 单例类智能有一个对象实例，如果某天，我们需要在代码中创建两个实例或多个实例，那就要对代码有比较大的改动。
- 如果我们将数据库连接池设计成单例类，显然无法适应这样的需求变更，也就是说，单例类在某些情况下会影响代码的扩展性、灵活性。
- 所以，数据库连接池、线程池这类的资源池，最好不要设计成单例类。实际上，一些开源的数据库连接池、线程池也确实没有设计成单例类。

### 单例对代码的可测试性不友好
- 单例模式的使用会影响到代码的可测试性。如果单例类依赖比较中的外部资源，如 DB，我们在写单元测试的时候，希望通过 mock 的方式将它替换掉。而单例类这种硬编码式的使用方式，导致无法实现 mock 替换。
- 如果单例类持有成员变量，那它实际上相当于一个全局变量，被所有的代码共享。

### 单例不支持有参数的构造函数
- 单例不支持有参数的构造函数，比如我们创建一个连接池的单例对象，我们没法通过参数来指定连接池的大小。
- **解决方法一：**
  - 创建完实例之后，再调用 init() 函数传递参数，**注意：** 我们在使用这个单例类的时候，要先调用 init() 方法，然后才能调用 getInstance() 方法，否则代码会抛出异常。
  - 代码示例：
    ```java
      public class Singleton {
      private static Singleton instance = null;
      private final int paramA;
      private final int paramB;

      private Singleton(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
      }

      public static Singleton getInstance() {
        if (instance == null) {
          throw new RuntimeException("Run init() first.");
        }
        return instance;
      }

      public synchronized static Singleton init(int paramA, int paramB) {
        if (instance != null){
          throw new RuntimeException("Singleton has been created!");
        }
        instance = new Singleton(paramA, paramB);
        return instance;
      }
    }

    Singleton.init(10, 50); // 先init，再使用
    Singleton singleton = Singleton.getInstance();  
    ```
  
- **解决方法二：**
  - 将参数放到 getInstance() 方法中。
  - 示例代码：
    ```java
    public class Singleton {
      private static Singleton instance = null;
      private final int paramA;
      private final int paramB;

      private Singleton(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
      }

      public synchronized static Singleton getInstance(int paramA, int paramB) {
        if (instance == null) {
          instance = new Singleton(paramA, paramB);
        }
        return instance;
      }
    }

    Singleton singleton = Singleton.getInstance(10, 50);
    // 此方法存疑，当调用多次时，参数传入的参数不同时，可能会出现与用户预期不一致的结果
    ```

- **解决方法三：（推荐方案👍）**
  - 将参数放到另外一个全局变量中。
  - Config 是一个存储了 paramA 和 paramB 值的全局变量。里面的值既通过静态常量来定义，也可以从配置文件中加载得到。
    ```java
    public class Config {
      public static final int PARAM_A = 123;
      public static final int PARAM_B = 245;
    }

    public class Singleton {
      private static Singleton instance = null;
      private final int paramA;
      private final int paramB;

      private Singleton() {
        this.paramA = Config.PARAM_A;
        this.paramB = Config.PARAM_B;
      }

      public synchronized static Singleton getInstance() {
        if (instance == null) {
          instance = new Singleton();
        }
        return instance;
      }
    }
    ```

## 单例的替代方案
- 为了保证全局唯一，除了使用单例，我们还可以用静态方法来实现。*这也是项目开发中经常用到的一种实现思路。* 
  - 静态方法的实现思路，并不能解决上面提到的问题。并且比单例更加不灵活——无法支持延迟加载。
    ```java
    public class IdGenerator{
      private static AtomicLong id = new AtomicLong(0);

      public static long getId(){
        return id.incrementAndGet();
      }
    }
    // 使用举例：
    long id = IdGenerator.getId();
    ```

- 如果要完全解决这些问题，我们可能要从根上，寻找其他方式来实现全局唯一类。
- 类对象的全局唯一性可以通过多种不同的方式来保证。除了单例模式，也可以通过工厂模式、IOC 容器来保证，还可以通过程序员自己来保证。


## 集群环境下的分布式单例模式
### 单例模式中的唯一性
- 定义中提到，“一个类只允许创建唯一一个对象。”之类的对象的唯一性，是进程内只允许创建唯一一个对象。
- 单例类中对象的唯一性的作用范围是进程内的，进程间是不唯一的。

### 线程唯一的单例
- 定义：在一个进程内，子线程 A 和子线程 B 中，两个实例是不同的，但在各自的线程中，对象的实例是唯一的。
- 实现方法：
  1. 通过一个 HashMap 来存储对象，其中 key 是线程 ID，value 是对象。这样就可以做到，不同线程对应不同的对象。
  2. Java 本身自带的 ThreadLocal 工具类，可以更加轻松地实现线程唯一单例。不过， ThreadLocal 底层实现原理也是基于 HashMap。
  ```java
  public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);

    private static final ConcurrentHashMap<Long, IdGenerator> instances
            = new ConcurrentHashMap<>();

    private IdGenerator() {}

    public static IdGenerator getInstance() {
      Long currentThreadId = Thread.currentThread().getId();
      instances.putIfAbsent(currentThreadId, new IdGenerator());
      return instances.get(currentThreadId);
    }

    public long getId() {
      return id.incrementAndGet();
    }
  }
  ```

  ### 集群环境下的单例实现方式
  - 定义：类比上面的线程唯一、进程唯一的概念，即，在整个集群环境中，这个类对象唯一。
    - 在不同的进程间共享同一个对象，不能创建同一个类的多个对象。

  - 实现思路：
    - 类比之前的线程间唯一的实现思路，线程间唯一，通过一个线程共享变量 HashMap 或者通过 ThreadLocal 来将线程跟对象进行绑定，实现线程间类对象唯一。
    - 集群环境下，我们需要依赖一个共享的外部存储区来充当 ThreadLocal 这样的角色。
      - 我们需要把这个单例对象序列化并存储到外部共享存储区（比如读取文件）。进程在使用这个单例对象的时候，需要先从外部共享存储区中将它读取到内存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。
      - 为保证任何时刻，在进程间只有一份对象存在，一个进程在获取到对象之后，需要对对象加锁，避免其他进程再将其获取。
      - 在进程使用完这个对象之后，还需要显示地将对象从内存中删除，并且释放对对象加的锁。
        ```java
        public class IdGenerator {
          private AtomicLong id = new AtomicLong(0);
          private static IdGenerator instance;
          private static SharedObjectStorage storage = FileSharedObjectStorage(/*入参省略，比如文件地址*/);
          private static DistributedLock lock = new DistributedLock();
          
          private IdGenerator() {}

          public synchronized static IdGenerator getInstance() 
            if (instance == null) {
              lock.lock();
              instance = storage.load(IdGenerator.class);
            }
            return instance;
          }
          
          public synchroinzed void freeInstance() {
            storage.save(this, IdGeneator.class);
            instance = null; //释放对象
            lock.unlock();
          }
          
          public long getId() { 
            return id.incrementAndGet();
          }
        }

        // IdGenerator使用举例
        IdGenerator idGeneator = IdGenerator.getInstance();
        long id = idGenerator.getId();
        IdGenerator.freeInstance();
        ```

### 实现一个多例模式
- 定义：与单例模式相对应地，“单例”指的是，一个类智能创建一个对象；“多例”则指的是，一个类可以创建多个对象，但是个数是有限制的，比如只能创建 3 个对象。
```java
public class BackendServer {
  private long serverNo;
  private String serverAddress;

  private static final int SERVER_COUNT = 3;
  private static final Map<Long, BackendServer> serverInstances = new HashMap<>();

  static {
    serverInstances.put(1L, new BackendServer(1L, "192.134.22.138:8080"));
    serverInstances.put(2L, new BackendServer(2L, "192.134.22.139:8080"));
    serverInstances.put(3L, new BackendServer(3L, "192.134.22.140:8080"));
  }

  private BackendServer(long serverNo, String serverAddress) {
    this.serverNo = serverNo;
    this.serverAddress = serverAddress;
  }

  public BackendServer getInstance(long serverNo) {
    return serverInstances.get(serverNo);
  }

  public BackendServer getRandomInstance() {
    Random r = new Random();
    int no = r.nextInt(SERVER_COUNT)+1;
    return serverInstances.get(no);
  }
}
```

- 对于多例模式，还有一种理解方式：同一类型的只能创建一个对象，不同类型的可以创建多个对象。通过一个例子来解释一下，在代码中，logger name 就是刚刚说的“类型”，同一个 logger name 获取到的对象实例是相同的，不同的 logger name 获取到的对象实例是不同的。
```java
public class Logger {
  private static final ConcurrentHashMap<String, Logger> instances
          = new ConcurrentHashMap<>();

  private Logger() {}

  public static Logger getInstance(String loggerName) {
    instances.putIfAbsent(loggerName, new Logger());
    return instances.get(loggerName);
  }

  public void log() {
    //...
  }
}

//l1==l2, l1!=l3
Logger l1 = Logger.getInstance("User.class");
Logger l2 = Logger.getInstance("User.class");
Logger l3 = Logger.getInstance("Order.class");
```

- 这种多例模式的理解方式有点类似工厂模式。不同的是：
  - 多例模式创建的对象都是同一个类的对象；
  - 而工厂模式创建的是不同子类的对象。

### 上面说道，单例唯一性的作用范围是进程，实际上，对 Java 语言来说，单例类对象的唯一性的作用范围并非进程，而是类加载器。为什么？