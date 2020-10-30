package domain.model

import java.util.UUID

case class User(id: UserId, name: UserName) {
  
  def changeName(newName: UserName): User = User(this.id, newName)
}

case object User {

//  // 再作成 は User
//  def apply(userId: UserId, userName: UserName): User =
//    User(userId, userName)
  
  // 新規作成
  def apply(userName: UserName): User =
    User(UserId(UUID.randomUUID.toString), userName)
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

