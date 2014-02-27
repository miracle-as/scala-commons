package dk.miracleas.scala


package object commons {


  //    implicit class SetCompanionOps[+A, B <: Iterable[A]](col: GenIterableLike[A, B]) extends AnyVal {
  /*  implicit class SetCompanionOps[A, C[A] <: Iterable[A]](ca: C[A]) {
      def exclusiveOrSplit(p: C[A])(
        implicit cbfcc: CanBuildFrom[C[A], C[A], C[C[A]]], cbfc: CanBuildFrom[C[A], A, C[A]]
        ): C[C[A]] = {
        val it = ca.iterator
        val cca = cbfcc()
        if (!it.hasNext) cca.result
        else {
          val as = cbfc()
          var olda = it.next
          as += olda
          while (it.hasNext) {
            val a = it.next
            if (p(a)) as += a
            else {cca += as.result; as.clear; as += a}
            olda = a
          }
          cca += as.result
        }
        cca.result
      }
    }*/


  //    def ^(other: B): (B, B) = (col -- other, other -- col)
  //    def exclusiveOrSplit(other: B): (B, B) = (col.filter(e=>other.contains(e))

  //    def -(other: B): B = col.filterNot(e=>other.contains(e))

  //  }


  implicit class GroupingCollection[A, C[A] <: Iterable[A]](ca: C[A]) {

    import collection.generic.CanBuildFrom

    /*
    from http://stackoverflow.com/questions/5410846/how-do-i-apply-the-enrich-my-library-pattern-to-scala-collections

        def groupedWhile(p: (A, A) => Boolean)(
          implicit cbfcc: CanBuildFrom[C[A], C[A], C[C[A]]], cbfc: CanBuildFrom[C[A], A, C[A]]
          ): C[C[A]] = {
          val it = ca.iterator
          val cca = cbfcc()
          if (!it.hasNext) cca.result
          else {
            val as = cbfc()
            var olda = it.next
            as += olda
            while (it.hasNext) {
              val a = it.next
              if (p(olda, a)) as += a
              else {cca += as.result; as.clear; as += a}
              olda = a
            }
            cca += as.result
          }
          cca.result
        }
    */

    def groupedWhile(p: A => Boolean)(
      implicit cbfcc: CanBuildFrom[C[A], C[A], C[C[A]]], cbfc: CanBuildFrom[C[A], A, C[A]]
      ): C[C[A]] = {
      val it = ca.iterator
      val cca = cbfcc()
      if (!it.hasNext) cca.result
      else {
        val as = cbfc()
        var olda = it.next
        as += olda
        while (it.hasNext) {
          val a = it.next
          if (p(a)) as += a
          else {cca += as.result; as.clear; as += a}
          olda = a
        }
        cca += as.result
      }
      cca.result
    }
  }

}
