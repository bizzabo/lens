object Main extends App{
  // usage example
  import ai.x.lens.ImplicitBoundLens
  case class Inner( i: Int )
  case class Outer( inner: Inner, s: String )
  val o = Outer(Inner(5), "foo")

  val actual = 
    o.lens(_.s).set("bar")
    .lens(_.inner.i).modify(_+1) // .lens calls can be chained nicely

  val expected = Outer(Inner(6),"bar")

  assert( expected == actual, s"$expected == $actual" )

  println(Console.GREEN + "Success" + Console.RESET)
}
