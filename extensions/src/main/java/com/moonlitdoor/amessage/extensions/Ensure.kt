package com.moonlitdoor.amessage.extensions

/**
 * Using a `when` expression over a [sealed] class doesn't guarantee that the compiler will detect when a subtype is missing. This happens
 * whenever a `when` expression is used as a statement, instead of an expression. Using [Ensure] will guarantee that all subtypes must be
 * taken care of.
 *
 * Usage:
 *      Ensure exhaustive when (someSealedClass) {
 *          .. guarantees that all subtypes will be taken care of
 *      }
 */
object Ensure {
  inline infix fun <reified T> exhaustive(any: T?) = any
}
