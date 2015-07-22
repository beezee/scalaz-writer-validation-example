package main

import scalaz._
import scalaz.Scalaz._

object WriterExample {

  type Logger[A] = Writer[List[String], A]
  type LoggedValidation[A] = Logger[Validation[String, A]]

  def validateAge(age: Int): LoggedValidation[Int] =
    (age > 19).fold(age.success, "Too young".failure)
      .set(List("Checking age"))

  def validateEmail(email: String): LoggedValidation[String] = {
    val validated = for {
      hasAt <- (email.contains("@")).fold(
        email.success, "Not a valid email".failure)
      longEnough <- (email.length > 3).fold(
        email.success, "Too short".failure)
      res = longEnough
    } yield res
    validated.set(List("Checking email"))
  }

  case class User(age: Int, email: String)

  def makeUser(age: Int, email: String): LoggedValidation[User] =
    for {
      vAge <- validateAge(age)
      vEmail <- validateEmail(email)
      user = (vAge |@| vEmail)(User(_, _))
    } yield user

}
