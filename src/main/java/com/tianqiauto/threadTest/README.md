# java 多线程相关知识
###☆首先需要知道线程相关的一些方法
####1.run方法

run()方法是不需要用户来调用的。当通过start()方法启动一个线程之后，一旦线程获得了CPU执行时间，便进入run()方法体去执行具体的任务。 一般来说，有两种方式可以达到重写run()方法的效果：
**直接重写**：直接继承Thread类并重写run()方法；
**间接重写**：通过Thread构造函数传入Runnable对象 (注意，实际上重写的是 Runnable对象 的run() 方法) 



**2.start方法**

start() 用来启动一个线程，当调用该方法后，相应线程就会进入就绪状态，该线程中的run()方法会在某个时机被调用。

 **3.sleep 方法**

调用sleep方法相当于让线程进入阻塞状态。该方法有如下两条特征： 

   如果调用了sleep方法，必须捕获InterruptedException异常或者将该异常向上层抛出； 

   sleep方法不会释放该线程所拥有的资源（例如：锁），也就是说如果当前线程持有对某个对象的锁，则即使调用sleep方法，其他线程也无法访问这个对象。

**4.join 方法**

 	当前线程调用其他线程的join方法，会阻塞当前线程，直到其他线程执行完毕，才会进入就绪状态。

   join方法是被Synchronized关键字所修饰，访问时，需要获得其他线程对象的锁，如果有两个线程同时调用另外一个线程的join方法，会有一个线程成功得到锁，而另外一个则必须等待，进入阻塞状态，而在得到锁之后，才会执行join方法。

   join()方法是通过wait()方法 (Object 提供的方法) 实现的。当 millis == 0 时，会进入 while(isAlive()) 循环，并且只要子线程是活的， 宿主线程就不停的等待。 join方法同样会会让线程交出CPU执行权限； join方法同样会让线程释放对一个对象持有的锁；

**5.yield 方法**

​	调用 yield()方法会让当前线程交出CPU资源，让CPU去执行其他的线程。但是，yield()不能控制具体的交出CPU的时间。需要注意的是，yield()方法只能让 拥有相同优先级的线程 有获取 CPU 执行时间的机会；

​    调用yield()方法并不会让线程进入阻塞状态，而是让线程重回就绪状态，它只需要等待重新得到 CPU 的执行；

​    它同样不会释放锁。



### **☆先补充一下概念：Java 内存模型中的可见性、原子性和有序性。**

**可见性：**

　　可见性是一种复杂的属性，因为可见性中的错误总是会违背我们的直觉。通常，我们无法确保执行读操作的线程能适时地看到其他线程写入的值，有时甚至是根本不可能的事情。为了确保多个线程之间对内存写入操作的可见性，必须使用同步机制。

　　**可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。**也就是一个线程修改的结果。另一个线程马上就能看到。比如：用volatile修饰的变量，就会具有可见性。volatile修饰的变量不允许线程内部缓存和重排序，即直接修改内存。所以对其他线程是可见的。但是这里需要注意一个问题，volatile只能让被他修饰内容具有可见性，但不能保证它具有原子性。比如 volatile int a = 0；之后有一个操作 a++；这个变量a具有可见性，但是a++ 依然是一个非原子操作，也就是这个操作同样存在线程安全问题。

　　在 Java 中 volatile、synchronized 和 final 实现可见性。

**原子性：**

　　**原子是世界上的最小单位，具有不可分割性。**比如 a=0；（a非long和double类型） 这个操作是不可分割的，那么我们说这个操作时原子操作。再比如：a++； 这个操作实际是a = a + 1；是可分割的，所以他不是一个原子操作。非原子操作都会存在线程安全问题，需要我们使用同步技术（sychronized）来让它变成一个原子操作。一个操作是原子操作，那么我们称它具有原子性。java的concurrent包下提供了一些原子类，我们可以通过阅读API来了解这些原子类的用法。比如：AtomicInteger、AtomicLong、AtomicReference等。

　　在 Java 中 synchronized 和在 lock、unlock 中操作保证原子性。

**有序性：**

　　Java 语言提供了 volatile 和 synchronized 两个关键字来保证线程之间操作的有序性，volatile 是因为其本身包含“禁止指令重排序”的语义，synchronized 是由“一个变量在同一个时刻只允许一条线程对其进行 lock 操作”这条规则获得的，此规则决定了持有同一个对象锁的两个同步块只能串行执行。



**☆Java内存模型**

[Java内存模型](https://www.jianshu.com/p/d52fea0d6ba5)告诉我们，各个线程会将共享变量从主内存中拷贝到工作内存，然后执行引擎会基于工作内存中的数据进行操作处理。线程在工作内存进行操作后何时会写到主内存中？这个时机对普通变量是没有规定的，而针对volatile修饰的变量给java虚拟机特殊的约定，线程对volatile变量的修改会立刻被其他线程所感知，即不会出现数据脏读的现象，从而保证数据的“可见性”。

现在我们有了一个大概的印象就是：**被volatile修饰的变量能够保证每个线程能够获取该变量的最新值，从而避免出现数据脏读的现象。**

volatile :易变的、不稳定的， 经关键字修饰的变量可以保证可见性有序性，不保证原子性





阅读项目代码中的两个测试例子，体会volatile的作用。





# Volatile和Synchronized四个不同点

1 粒度不同，前者针对变量 ，后者锁对象和类
2 Synchronized阻塞，volatile线程不阻塞
3 Synchronized保证三大特性，volatile不保证原子性
4 Synchronized编译器优化，volatile不优化 



同步：就是一个任务的完成需要依赖另外一个任务，只有等待被依赖的任务完成后，依赖任务才能完成。
异步：不需要等待被依赖的任务完成，只是通知被依赖的任务要完成什么工作，只要自己任务完成了就算完成了，被依赖的任务是否完成会通知回来。（异步的特点就是通知）。 打电话和发短信来比喻同步和异步操作。
阻塞：CPU停下来等一个慢的操作完成以后，才会接着完成其他的工作。
非阻塞：非阻塞就是在这个慢的执行时，CPU去做其他工作，等这个慢的完成后，CPU才会接着完成后续的操作。
非阻塞会造成线程切换增加，增加CPU的使用时间能不能补偿系统的切换成本需要考虑。