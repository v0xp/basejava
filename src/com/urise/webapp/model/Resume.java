package com.urise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private  final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private  final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);


    public Resume(String fullName){
       this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getContactType(ContactType type) {
        return contacts.get(type);
    }

    public Section getSectionType(SectionType type) {
        return sections.get(type);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Resume resume = (Resume) o;
//
//        return uuid.equals(resume.uuid);
//    }
//
//    @Override
//    public int hashCode() {
//        return uuid.hashCode();
//    }


    @Override
    public String toString() {
        return uuid + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
