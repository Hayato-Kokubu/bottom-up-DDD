package domain.model

import java.util.UUID

case class User(id: UserId, name: UserName) {
}

case object User {
  def apply(userName: String): User =
    User(UserId(UUID.randomUUID.toString), UserName(userName))
}

case class UserId(value: String)
case class UserName(value: String)

case object UserName {
  def apply(str: String): UserName =
    if( validateAlpabets(str) && isLergerThan(2, str)) new UserName(str)
    else throw new IllegalArgumentException(s"UserName = ($str)")
  
  def validateAlpabets(str: String): Boolean = str.matches("""^[a-zA-Z]*$""")
  def isLergerThan(num: Int, str: String): Boolean = str.length > num
}

