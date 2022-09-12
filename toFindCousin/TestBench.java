package toFindCousin;

public class TestBench {
  public static void main(String args[]) {
    new FamilyMember("John", 'M', "Brad", "Lisa");
    new FamilyMember("Emma", 'F', "Brad", "Lisa");
    new FamilyMember("Alex", 'M', "John", "Jenny");
    new FamilyMember("Emily", 'F', "Steve", "Emma");
    new FamilyMember("Rachel", 'F', "Steve", "Emma");
    new FamilyMember("Jonnah", 'F', "Steve", "Emma");
    new FamilyMember("Balaji", 'M', "Baskar", "Lakshmi");
    new FamilyMember("Kavi", 'F', "Suresh", "Kalai");
    new FamilyMember("Lakshmi", 'F', "Muthu", "Kala");
    new FamilyMember("Suresh", 'M', "Muthu", "Kala");
    FamilyMember.findCousin("Balaji");
  }
}
