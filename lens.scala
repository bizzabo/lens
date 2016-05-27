package ai.x.lens

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros
import monocle._
import monocle.syntax.ApplyLens

object BoundLens {
  def apply[S]( value: S ) = new BoundLens( ApplyLens[S, S, S, S]( value, Lens.id ) )
}

/** Syntactic sugar around an ApplyLens */
class BoundLens[S, A]( val applyLens: ApplyLens[S, S, A, A] ) extends AnyVal {
  def apply[C]( field: A => C ): BoundLens[S, C] = macro BoundLensMacros.apply[S, A, C]
  def set( value: A ): S = applyLens.set( value )
  def modify( diff: A => A ): S = applyLens.modify( diff )
}

object BoundLensMacros {
  def apply[S, A: c.WeakTypeTag, C]( c: Context )( field: c.Expr[A => C] ) = {
    import c.universe._
    c.Expr[BoundLens[S, C]]( q"""
      new _root_.ai.x.lens.BoundLens(
        ${c.prefix.tree}.applyLens composeLens
          _root_.monocle.macros.GenLens[${c.weakTypeOf[A]}](${field})
      )
    """ )
  }
}
