package cats

/**
  * 名词定义：
  * 集合：可以等价于类型，比方说：Integer，整形，就是整数集合。 String,字符串类型，就是字符串集合。
  * 操作：
  */

/**
  * A semigroup is any set `A` with an associative operation (`combine`).
  * 半群是一个拥有一个组合操作的集合。这个组合操作必须满足结合律。
  * 例如：("aaa" + "bbb") + "ccc" == "aaa" + ("bbb" + "ccc").
  * 我们小学的时候，经常利用这种性质来计算：
  * 1 +  (-1) + 2 + (-2) ... = 0, 因为加法满足结合律，我们可以简化为：
  * (1 + (-1)) + (2 + (-2)) ... = 0 
  */
trait Semigroup[A] {
  def combine(x: A, y: A): A
}

/**
  * A monoid is a semigroup with an identity.
  * 幺半群就是一个含有幺元的半群（基本上是重言式解释）。
  * 幺就是一的意思，这个“幺元”，就是集合中的某个元素，这个元素与集合中的及其它元素集合组合后，那个元素不变。
  * 比如:
  *   整数（集合）的加法（操作）上，0就是幺元，（你可以看到词语混乱了） 100 + 0 == 100 
  *   整数（几个）的乘法（操作）上，1就是幺元                       100 * 1 == 100
  *   字符串（集合)的连接（操作）上， 空字符串""就是幺元             "hello" + "" == "hello"
  * 
  * 知道幺元的存在，我们就可以对0个元素进行组合。
  */
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

/**
  * A group is a monoid where each element has an inverse.
  * 群就是集合中每一个元素都有逆元的操作。
  * 逆元，就是对某个元素进行的操作可以进行撤销。
  * 比如： 整数加法里面，可以  用 +(-100) 来冲销 + 100的操作，-100就是100的逆元。
  *       字符串组合操作无法撤销，所以字符串上的组合操作，不能构成一个群。
  *       HARD：整数上面的乘法，也不可以构成群，因为乘上一个整数，需要乘上一个分数来冲销，分数不是整数。
  *             如果定义分数上面的乘法，就可以构成一个群了。
  */
trait Group[A] extends Monoid[A] {
  def reverse(x: A): A
}

/** 交换半群： 组合操作可以颠倒两个元素顺序
  * 整数上的加法是可交换的: 1 + 2 == 2 + 1
  * 字符串的链接是不可以交换的: "hello" + "world" != "word" + "hello"
  */ 
trait CommutativeSemigroup[A] extends Semigroup[A]

/** 交换幺半群：解释同上*/
trait CommutativeMonoid[A] extends Monoid[A] with CommutativeSemigroup[A]

/** 交换群: 解释同上 */
trait CommutativeGroup[A] extends Group[A] with CommutativeMonoid[A]



/**
  * Bands are semigroups whose operation (i.e. combine) is also idempotent.
  * 带：带就是有一个幂等操作的半群。
  * 幂：x的n次方叫做幂，幂等就是你多操作几次，值还是不变。
  * 例如： 1 max 2 = 2， (((1 max 2) max 2) .. max 2)一直无穷还是2.
  *       如果把操作次数作为x轴，结果作为y轴，画出来就是一条线，如果操作0次也算进去，就是y= 1到2之间的一条带子。
  */
trait Band[A] extends Semigroup[A]

