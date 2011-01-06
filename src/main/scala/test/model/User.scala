package test.model

import net.liftweb.record.{MetaRecord, Record, MegaProtoUser, MetaMegaProtoUser}
import net.liftweb.record.field.{LongField, LongTypedField, OptionalDateTimeField, OptionalIntField, StringField}
import net.liftweb.squerylrecord.KeyedRecord
import net.liftweb.squerylrecord.RecordTypeMode._

import net.liftweb.common._
import net.liftweb.util.FieldError

import org.squeryl.Query
import org.squeryl.annotations.Column

class User extends MegaProtoUser[User] /* with KeyedRecord[Long] */ {
    def meta = User

    protected def valUnique(errorMsg: => String)(email: String): List[FieldError]  = {
      List[FieldError]()
    }



    override def toString = "User"
}

object User extends User with MetaMegaProtoUser[User] {


  override def screenWrap = Full(<lift:surround with="default" at="content">
			       <lift:bind /></lift:surround>)



    protected def userFromStringId(id: String): Box[User] = from(TestSchema.users) (u =>
      where(u.id === id.toLong)
      select(u)
    ).headOption

    protected def findUserByUserName(email: String): Box[User] = from(TestSchema.users) (u =>
      where(u.email === email)
      select(u)
    ).headOption

    protected def findUserByUniqueId(id: String): Box[User] = userFromStringId(id)

}
