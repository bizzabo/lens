## lens

[![Join the chat at https://gitter.im/xdotai/lens](https://badges.gitter.im/xdotai/lens.svg)](https://gitter.im/xdotai/lens?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

User-friendly lens syntax for mortals. Update values inside of nested case class instances without .copy .

(Based on [Monocle](https://github.com/julien-truffaut/Monocle))
### SBT Dependency

`"ai.x" %% "lens" % "1.0.0"`

### Usage

```scala
case class C( d: Int )
case class B( c: C )
case class A( b: B )
val a = A(B(C(3)))
```

#### Easy updates using `.lens`
```scala
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

