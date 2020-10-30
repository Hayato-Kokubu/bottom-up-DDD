package infrastructure.repository

import domain.model.{IUserFactory, User, UserId, UserName}
import scalikejdbc._

// 自走採番( UserSeq を用いてつくる方法 )
class AutoIncrementUserFactory () extends IUserFactory {
  // Todo 共通化
  Class.forName("com.mysql.cj.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost/bottom_up_ddd?useSSL=false", "bottom_up_ddd_user", "bottom_up_ddd")
  
  implicit val session = AutoSession
  val us = UserSeqRecord.syntax("us")
  val cols = UserSeqRecord.column
  
  
  override def create(userName: UserName): User = {

    val id = {
      val newIdNum = {
        withSQL{
          insert.into(UserSeqRecord)
            .namedValues(
              cols.sequence -> null
            )
        }.update.apply()
        sql"select LAST_INSERT_ID()".map(rs => rs.long(1)).single.apply().getOrElse(throw new IllegalStateException("採番時のエラー"))
      }
      newIdNum
      
      UserId(convertNumToUUIDString(newIdNum))// UserSeq から 値を取得 -> convertNumToUUIDString で変換した文字列
    }
    
    User(id, userName)
  }
  
  
  private def convertNumToUUIDString(num: Long): String = {
    val str = "%032d".format(num)
    
    val (s1, temp1) = str.splitAt(8)
    val (s2, temp2) = temp1.splitAt(4)
    val (s3, temp3) = temp2.splitAt(4)
    val (s4, s5) = temp3.splitAt(4)
    
    s"$s1-$s2-$s3-$s4-$s5"
  }
  
}

case class UserSeqRecord(
  sequence: Long
)

object UserSeqRecord extends SQLSyntaxSupport[UserSeqRecord] {
  override val tableName = "UserSeq"
  override val columns = Seq("sequence")
  
  def apply(st: ResultName[UserSeqRecord])(rs: WrappedResultSet): UserSeqRecord =
    autoConstruct(rs, st)
}
