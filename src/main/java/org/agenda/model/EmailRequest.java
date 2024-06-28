package org.agenda.model;

public class EmailRequest {
    private String title;
    private String message;
    private String contacts;
    private String subject;
    private String nameProjectOrNameBusiness;

    public EmailRequest(String title, String message, String contacts, String subject, String nameProjectOrNameBusiness) {
        this.title = title;
        this.message = message;
        this.contacts = contacts;
        this.subject = subject;
        this.nameProjectOrNameBusiness = nameProjectOrNameBusiness;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNameProjectOrNameBusiness() {
        return nameProjectOrNameBusiness;
    }

    public void setNameProjectOrNameBusiness(String nameProjectOrNameBusiness) {
        this.nameProjectOrNameBusiness = nameProjectOrNameBusiness;
    }
}
