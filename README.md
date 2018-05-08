## lens

[![Join the chat at https://gitter.im/xdotai/lens](https://badges.gitter.im/xdotai/lens.svg)](https://gitter.im/xdotai/lens?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

User-friendly lens syntax for mortals. Update values inside of nested case class instances without .copy .

Based on [Monocle](https://github.com/julien-truffaut/Monocle).
Also see https://github.com/julien-truffaut/Monocle/pull/208 and https://github.com/julien-truffaut/Monocle/pull/204 .

**Use with caution.** `.copy` and likewise `.lens` can make code hard to read.
They are conceptually creating patched versions of existing objects.
It can be hard for readers to reason about the patching, similar to how it is hard to reason about mutation.
If you can write your code in a way that does not need patching objects, prefer that.
If you can't, `.lens` is a nice alternative to `.copy`.


### SBT Dependency

#### Scala 2.11

`"ai.x" %% "lens" % "1.0.0"`

#### Scala 2.12

`"ai.x" %% "lens" % "2.0.0"`

### Usage

```scala
case class C( d: Int )
case class B( c: C )
case class A( b: B )
val a = A(B(C(3)))
```

#### Easy updates using `.lens`
```scala
import ai.x.lens.ImplicitBoundLens
A(B(C(5))) == a.lens(_.b.c.d).set( 5 )
A(B(C(6))) == a.lens(_.b.c.d).modify( _ + 3 )
```

#### Verbose updates using `.copy`
```scala
A(B(C(5))) == a.copy(
  b = a.b.copy(
    c = a.b.c.copy(
      d = 5
)))

A(B(C(6))) == a.copy(
  b = a.b.copy(
    c = a.b.c.copy(
      d = a.b.c.d + 3
)))
```

### Related Work

ai.x.lens is very similar to [quicklens](https://github.com/adamw/quicklens),
which implements more features to slightly reduce code size. But this also mean more learning and remembering.
It's fair to make the trade-off in either direction. Check out quicklens if you want more features.
Use ai.x.lens if you want something simpler.
