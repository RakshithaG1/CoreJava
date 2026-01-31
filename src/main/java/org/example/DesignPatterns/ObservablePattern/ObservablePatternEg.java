package org.example.DesignPatterns.ObservablePattern;

import java.util.ArrayList;
import java.util.List;

//Observer registers → Subject state changes → notifyObservers() → update() called
//Observer interface--->Defines update contract
interface Observer {
    void update(String message);
}

// Subject (Observable)---->Maintains observers List
//Concrete Subject----->Changes state and trigger notify
class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    private String latestNews;

    // Register observer
    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println("Observer attached: " + observer);
    }

    // Unregister observer
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer detached: " + observer);
    }

    // Notify all observers
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }

    // Subject state changes
    public void setNews(String news) {
        this.latestNews = news;
        System.out.println("News Agency: Breaking news - " + news);
        notifyObservers();
    }

    public String getNews() {
        return latestNews;
    }
}

// Concrete Observers--> Reacts to updates
class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String message) {
        System.out.println(appName + " received notification: " + message);
    }

    @Override
    public String toString() {
        return appName;
    }
}

class EmailAlert implements Observer {
    private String email;

    public EmailAlert(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Email sent to " + email + ": " + message);
    }

    @Override
    public String toString() {
        return "Email: " + email;
    }
}

class SMSAlert implements Observer {
    private String phoneNumber;

    public SMSAlert(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String message) {
        System.out.println("SMS sent to " + phoneNumber + ": " + message);
    }

    @Override
    public String toString() {
        return "SMS: " + phoneNumber;
    }
}

// Usage
public class ObservablePatternEg {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        // Attach observers
        Observer app = new MobileApp("CNN App");
        Observer email = new EmailAlert("user@example.com");
        Observer sms = new SMSAlert("+1234567890");

        agency.attach(app);
        agency.attach(email);
        agency.attach(sms);

        // Break news
        System.out.println("\n--- Breaking News ---");
        agency.setNews("Major earthquake in Tokyo");

        // Detach observer
        System.out.println("\n--- Detaching SMS Alert ---");
        agency.detach(sms);

        System.out.println("\n--- More News ---");
        agency.setNews("Stock market rises");
    }
}
