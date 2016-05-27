## lens

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
a.lens(_.b.c.d).set( 5 )
a.lens(_.b.c.d).modify( _ + 2 )
```

#### Verbose update using `.copy`
```scala
a.copy(
  b = a.b.copy(
    c = a.b.c.copy(
      d = 5
)))
a.copy(
  b = a.b.copy(
    c = a.b.c.copy(
      d = a.b.c.d + 2
)))
```

