package toFindCousin;

import java.util.ArrayList;
import java.util.HashMap;

public class FamilyMember {
  public static HashMap<String, FamilyMember> familyRef = new HashMap<>();
  private String name;
  private char gender;
  private FamilyMember father;
  private FamilyMember mother;
  private ArrayList<FamilyMember> sons = new ArrayList<FamilyMember>();
  private ArrayList<FamilyMember> daughters = new ArrayList<FamilyMember>();
  private ArrayList<FamilyMember> brothers = new ArrayList<FamilyMember>();
  private ArrayList<FamilyMember> sisters = new ArrayList<FamilyMember>();

  FamilyMember(String name, char gender, String fatherName, String motherName) {
    if (familyRef.get(name) == null) {
      this.name = name;
      this.gender = gender;
      familyRef.put(name, this);
    }
    familyRef.get(name).father = setParent(fatherName, 'M', familyRef.get(name), true);
    familyRef.get(name).mother = setParent(motherName, 'F', familyRef.get(name), false);
  }

  public String getName() {
    return name;
  }

  public char getGender() {
    return gender;
  }

  public FamilyMember getFather() {
    return father;
  }

  public FamilyMember getMother() {
    return mother;
  }

  public ArrayList<FamilyMember> getSons() {
    return sons;
  }

  public ArrayList<FamilyMember> getDaughters() {
    return daughters;
  }

  public ArrayList<FamilyMember> getBrothers() {
    return brothers;
  }

  public ArrayList<FamilyMember> getSisters() {
    return sisters;
  }

  public static FamilyMember setParent(String parent, char gender, FamilyMember child, boolean isFather) {
    if (parent == null) {
      return null;
    }
    if (FamilyMember.familyRef.get(parent) != null) {
      // to add son to parent
      addchild(FamilyMember.familyRef.get(parent), child);
      if (isFather) { // to add the siblings only one time
        // relate siblings according to the gender
        relateSiblings(familyRef.get(parent), child);
      }
    } else {
      familyRef.put(parent, new FamilyMember(parent, gender, null, null));
      addchild(familyRef.get(parent), child);
    }
    return familyRef.get(parent);
  }

  public static void addchild(FamilyMember parent, FamilyMember child) {
    if (child.getGender() == 'M') {
      parent.getSons().add(child);
    } else {
      parent.getDaughters().add(child);
    }
  }

  public static void relateSiblings(FamilyMember parent, FamilyMember child) {
    if (child.gender == 'M') {
      for (FamilyMember daughter : parent.getDaughters()) {
        daughter.getBrothers().add(child);
        child.getSisters().add(daughter);
      }
    } else {
      for (FamilyMember son : parent.getSons()) {
        son.getSisters().add(child);
        child.getBrothers().add(son);
      }
    }
  }

  public static void findCousin(String name) {
    FamilyMember member = familyRef.get(name);
    boolean isFound = false;
    if (member == null) {
      System.out.println("Wrong member name");
      return;
    }
    char gender = member.getGender();
    // to find from father's side
    for (FamilyMember aunt : member.getFather().getSisters()) {
      // to find cousin girl for cousin boy
      if (gender == 'M') {
        for (FamilyMember cousin : aunt.getDaughters()) {
          isFound = true;
          System.out.println(cousin.getName());
        }
      }
      // to find cousin boy for cousin girl
      else {
        for (FamilyMember cousin : aunt.getSons()) {
          isFound = true;
          System.out.println(cousin.getName());
        }
      }
    }
    // to find from mothers's side
    for (FamilyMember uncle : member.getMother().getBrothers()) {
      // to find cousin girl for cousin boy
      if (gender == 'M') {
        for (FamilyMember cousin : uncle.getDaughters()) {
          isFound = true;
          System.out.println(cousin.getName());
        }
      }
      // to find cousin boy for cousin girl
      else {
        for (FamilyMember cousin : uncle.getSons()) {
          isFound = true;
          System.out.println(cousin.getName());
        }
      }
    }
    // if not any cousins found
    if (isFound == false) {
      System.out.println("Could not found!");
    }
  }
}
