package ai.x.lens
object `package`{
  implicit class ImplicitBoundLens[T]( v: T ) {
    def lens = BoundLens( v )
  }
}

