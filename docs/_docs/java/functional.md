# Functional Programming & Currying

## Functional Programming

- [reference](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-frame.html)

### Supplier<T>

_Represents a supplier of results.There is no requirement that a new or distinct result be returned each time the
supplier is invoked.This is a functional interface whose functional method is get()._

```java
public class TimeUtils
{
    public static Supplier<Long> NOW = ()->
            Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

    public static <T> T getTime(Supplier<T> supplier)
    {
        return supplier.get();
    }

    public static void main(String[] args)
    {
        final Long time = getTime(NOW);
    }
}
```

### Predicate<T>

_Represents a predicate (boolean-valued function) of one argument.This is a functional interface whose functional method
is test(Object)._

```java
public class Utils
{
    public static Predicate<Integer> positive = (i)->i > 0;
    public static Predicate<Integer> negative = (i)->i < 0;
    public static Predicate<Integer> zero = positive.negate().and(negative.negate());
    public static Predicate<Integer> zero1 = positive.or(negative).negate();

    public static boolean number(Integer i, Predicate<Integer> positive)
    {
        return positive.test(i);
    }

    public static Predicate<Integer> number()
    {
        return (i)->i > 0;
    }

    public static void main(String[] args)
    {
        System.err.println(number(-1, negative));
        System.err.println(number(1, positive));
        System.err.println(number(0, zero));
        System.err.println(number(-0, zero1));
        System.err.println(number().test(1));
    }
}
```

### BiPredicate<T,U>

_Represents a predicate (boolean-valued function) of two arguments. This is the two-arity specialization of
Predicate.This is a functional interface whose functional method is test(Object, Object)._

```java
public class Utils
{
    public static Predicate<Integer> isEquals(Integer i)
    {
        return i::equals;
    }

    public static BiPredicate<Integer, Integer> isEquals1 = Integer::equals;

    public static Boolean isEquals2(Integer i, Integer j, BiPredicate<Integer, Integer> predicate)
    {
        return predicate.test(i, j);
    }

    public static Predicate<Integer> isEquals2(Integer i)
    {
        return j->j.equals(i);
    }

    public static void main(String[] args)
    {
        System.err.println(isEquals(1).test(2));

        System.err.println(isEquals1.test(1, 2));

        System.err.println(isEquals2(1, 2, isEquals1));

        System.err.println(isEquals2(1).test(2));
    }
}
```

### Consumer<T>

_Represents an operation that accepts a single input argument and returns no result. Unlike most other functional
interfaces, Consumer is expected to operate via side-effects.This is a functional interface whose functional method is
accept(Object)._

```java
public class Utils
{
    public static Consumer<PO> updateTime = po->po.setUpdateTime("update");

    public static void setUpdateTime(PO po, Consumer<PO> consumer)
    {
        consumer.accept(po);
    }

    public static Consumer<PO> setUpdateTime1()
    {
        return p->p.setUpdateTime("update");
    }

    public static void main(String[] args)
    {
        PO po = PO.builder().build();
        updateTime.accept(po);
        System.err.println(po.toString());

        PO po1 = PO.builder().build();
        setUpdateTime(po1, updateTime);
        System.err.println(po1.toString());

        PO po2 = PO.builder().build();
        setUpdateTime1().accept(po2);
        System.err.println(po2.toString());
    }
}

@Data
@Builder
class PO
{
    String desc;
    String createTime;
    String updateTime;
}

@Data
@Builder
class VO
{
    String desc;
}
```

### BiConsumer<T,U>

_Represents an operation that accepts two input arguments and returns no result. This is the two-arity specialization of
Consumer. Unlike most other functional interfaces, BiConsumer is expected to operate via side-effects.This is a
functional interface whose functional method is accept(Object, Object)._

```java
public class Utils
{
    public static BiConsumer<PO, VO> VO2PO = (po, vo)->po.setDesc(vo.getDesc());

    public static void VO2PO(PO po, VO vo, BiConsumer<PO, VO> consumer)
    {
        consumer.accept(po, vo);
    }

    public static Consumer<VO> VO2PO(PO po)
    {
        return (vo)->po.setDesc(vo.getDesc());
    }

    public static void main(String[] args)
    {
        VO vo = VO.builder().desc("v").build();
        PO po = PO.builder().build();
        VO2PO.accept(po, vo);
        System.err.println(po.toString());

        PO po1 = PO.builder().build();
        VO2PO(po1, vo, VO2PO);
        System.err.println(po1.toString());

        PO po2 = PO.builder().build();
        VO2PO(po2).accept(vo);
        System.err.println(po2.toString());
    }
}

@Data
@Builder
class PO
{
    String desc;
    String createTime;
    String updateTime;
}

@Data
@Builder
class VO
{
    String desc;
}
```

### Function<T,R>

_Represents a function that accepts one argument and produces a result.This is a functional interface whose functional
method is apply(Object)._

```java
public class TimeUtils
{
    public static Function<LocalDateTime, Long> getLongTime = t->
            Long.parseLong(t.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

    public static Long getLongTime(LocalDateTime time, Function<LocalDateTime, Long> function)
    {
        return function.apply(time);
    }

    public static Function<LocalDateTime, Long> getLongTime()
    {
        return t->Long.parseLong(t.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    public static void main(String[] args)
    {
        final LocalDateTime now = LocalDateTime.now();
        System.err.println(getLongTime.apply(now));
        System.err.println(getLongTime(now, getLongTime));
        System.err.println(getLongTime().apply(now));
    }
}
```

### BiFunction<T,U,R>

_Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of
Function. This is a functional interface whose functional method is apply(Object, Object)._

```java
public class TimeUtils
{

    public static BiFunction<LocalDateTime, String, Long> getLongTime = (t, s)->
            Long.parseLong(t.format(DateTimeFormatter.ofPattern(s)));

    public static Long getLongTime(LocalDateTime time, String pattern, BiFunction<LocalDateTime, String, Long> function)
    {
        return function.apply(time, pattern);
    }

    public static Function<LocalDateTime, Long> getLongTime(String pattern)
    {
        return t->Long.parseLong(t.format(DateTimeFormatter.ofPattern(pattern)));
    }

    public static Function<String, Long> getLongTime(LocalDateTime time)
    {
        return s->Long.parseLong(time.format(DateTimeFormatter.ofPattern(s)));
    }

    public static void main(String[] args)
    {
        final LocalDateTime now = LocalDateTime.now();
        final String pattern = "yyyyMMddHHmmss";
        System.err.println(getLongTime.apply(now, pattern));
        System.err.println(getLongTime(now, pattern, getLongTime));
        System.err.println(getLongTime(now).apply(pattern));
        System.err.println(getLongTime(pattern).apply(now));
    }
}
```

## Currying

```java
public class Currying
{
    public static BiFunction<LocalDateTime, String, Long> getLongTime = (t, s)->
            Long.parseLong(t.format(DateTimeFormatter.ofPattern(s)));

    public static Function<LocalDateTime, Long> getLongTime(String pattern)
    {
        return t->Long.parseLong(t.format(DateTimeFormatter.ofPattern(pattern)));
    }

    public static Function<String, Long> getLongTime(LocalDateTime time)
    {
        return s->Long.parseLong(time.format(DateTimeFormatter.ofPattern(s)));
    }
}
```

## [HOME](../index.md)
