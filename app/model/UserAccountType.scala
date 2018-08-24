package model

case object Organisation extends UserAccountType with Organ

case object Agent extends UserAccountType with Agent

case object Individual extends UserAccountType with Individual

trait UserAccountType {
  def get: String
}
trait Organ {
  def get: String = {
    "Organisations"
  }
}
trait Agent {
  def get: String = {
    "Agent"
  }
}
trait Individual {
  def get: String = {
    "Individual"
  }
}
