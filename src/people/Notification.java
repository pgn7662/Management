package people;

import constants.NotificationType;

public class Notification {
    private String senderName;
    private String notificationHeading;
    private NotificationType notificationType;

    public Notification(String senderName, String notificationHeading, NotificationType notificationType) {
        this.senderName = senderName;
        this.notificationHeading = notificationHeading;
        this.notificationType = notificationType;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getNotificationHeading() {
        return notificationHeading;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }
}
