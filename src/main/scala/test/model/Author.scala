package test.model

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field.{OptionalIntField, LongField, LongTypedField, StringField}
import net.liftweb.squerylrecord.KeyedRecord
import org.squeryl.PrimitiveTypeMode._

import org.squeryl.{KeyedEntity, Query}

class Author extends Record[Author] with KeyedRecord[Long] {
    def meta = Author

    val id = new LongField(this, 100)
    val name = new StringField(this, "")
    val age = new OptionalIntField(this)

    def books: Query[Book] = TestSchema.books.where(_.authorId.value === id.value)
}

object Author extends Author with MetaRecord[Author] {
    def createRecord = new Author
}
