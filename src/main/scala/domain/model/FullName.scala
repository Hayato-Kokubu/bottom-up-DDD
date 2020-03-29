package domain.model

case class FullName (
  firstName: String,
  lastName: String,
) {
	override def toString: String = s"$firstName $lastName"
}
