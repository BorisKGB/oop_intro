package human;

import family.Family;

import java.io.Serializable;
import java.util.*;

public class Human implements Serializable {
    private String fullName;
    private Gender gender;
    private Calendar birthDate;
    private Calendar deathDate;
    private Set<Family> families;
    private Map<Connection, Set<Human>> connections;

    public Human(String fullName, Gender gender, Calendar birthDate, Calendar deathDate, Family family) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.families = new HashSet<>();
        if (family != null) this.families.add(family);
        connections = new HashMap<Connection, Set<Human>>();
    }
    public Human(String fullName, Gender gender, Calendar birthDate, Calendar deathDate) {
        this(fullName, gender, birthDate, deathDate, null);
    }
    public Human(String fullName, Gender gender, Calendar birthDate, Family family) {
        this(fullName, gender, birthDate, null, family);
    }
    public Human(String fullName, Gender gender, Family family) {
        this(fullName, gender, null, null, family);
    }
    public Human(String fullName, Gender gender) {
        this(fullName, gender, null, null, null);
    }

    /**
     * Add to Family connection
     * @param family which family to connect
     */
    public void addFamily(Family family) {
        this.families.add(family);
    }

    /**
     * Add 'connection' to 'human
     */
    public void addConnection(Human human, Connection connection) {
        if (!connections.containsKey(connection)) {
            connections.put(connection, new HashSet<>());
        }
        connections.get(connection).add(human);
    }

    public String getName() {
        return fullName;
    }

    public void changeName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public Calendar getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Calendar deathDate) {
        this.deathDate = deathDate;
    }

    public Set<Human> getRelatedMembers(Connection connection) {
        return connections.get(connection);
    }

    public Set<Family> getFamilies() {
        return families;
    }

    private String getFancyDate(Calendar calendar) {
        return String.format("%02d.%02d.%d",
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(fullName, human.fullName) && gender == human.gender && Objects.equals(birthDate, human.birthDate) && Objects.equals(deathDate, human.deathDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, gender, birthDate, deathDate);
    }

    @Override
    public String toString() {
        if (fullName == null && gender == null && birthDate == null && deathDate == null) return "Нет данных";
        String datesString;
        if (birthDate == null) {
            datesString = "Неизвестно";
        } else {
            if (deathDate == null) {
                datesString = getFancyDate(birthDate);
            } else {
                datesString = String.format("%s - %s", getFancyDate(birthDate), getFancyDate(deathDate));
            }
        }
        return String.format("%s, %s", fullName, datesString);
    }
}