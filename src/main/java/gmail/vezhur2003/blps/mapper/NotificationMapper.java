package gmail.vezhur2003.blps.mapper;

import gmail.vezhur2003.blps.DTO.NotificationData;

import java.util.ArrayList;

public class NotificationMapper {
    public static String toEmailBody(ArrayList<ArrayList<NotificationData>> notificationList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p><h2>Добрый день, для вас появились новые вакансии</h2>\n");
        for (ArrayList<NotificationData> notificationListByTag : notificationList) {
            sb.append("<h4>");
            sb.append(notificationListByTag.get(0).getTag());
            sb.append("</h4>\n");
            sb.append("<blockquote>\n");
            for (NotificationData notification : notificationListByTag) {
                sb.append(notification.getName());
                sb.append("<br>\n");
            }
            sb.append("</blockquote>\n");
        }
        return sb.toString();
    }
}
