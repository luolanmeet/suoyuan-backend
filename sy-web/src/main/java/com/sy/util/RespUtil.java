package com.sy.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cck.Diray;
import com.cck.User;
import com.object.resp.DirayResp;
import com.object.resp.UserAllDirayResp;
import com.object.resp.UserDirayResp;
import com.object.resp.UserMsgAndDiray;

/**
 *
 * @author cck
 */
@Component
public class RespUtil {

    final static ThreadLocal<SimpleDateFormat> DATE_FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy年MM月dd号 EEEE"));

    final static ThreadLocal<SimpleDateFormat> TIME_FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm"));

    final static ThreadLocal<SimpleDateFormat> YEAR_FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("- yyyy年 -"));

    final static ThreadLocal<SimpleDateFormat> MONTH_FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("MM月"));

    final static ThreadLocal<SimpleDateFormat> DAY_FORMATTER
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd"));

    public UserMsgAndDiray getUserIndexResp(User user, List<Diray> dirays) {

        String dateStr = "";
        List<DirayResp> dirayResps = new ArrayList<>();

        if (!dirays.isEmpty()) {
            Date date = dirays.get(0).getWriteTime();
            dateStr = DATE_FORMATTER.get().format(date);

            for (Diray diray : dirays) {
                DirayResp dirayResp = DirayResp.builder()
                        .time(TIME_FORMATTER.get().format(diray.getWriteTime()))
                        .content(diray.getContent())
                        .build();
                dirayResps.add(dirayResp);
            }
        }

        UserMsgAndDiray resp = UserMsgAndDiray.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .avator(user.getAvator())
                .signature(user.getSignature())
                .dirayCount(user.getDirayCount())
                .lastDirayDate(dateStr)
                .dirays(dirayResps)
                .build();

        return resp;
    }

    public List<UserAllDirayResp> getUserDiraysResq(List<Diray> dirays) {

        Map<String, UserAllDirayResp> map = new LinkedHashMap<>();
        for (Diray diray : dirays) {
            Date date = diray.getWriteTime();
            String year    = YEAR_FORMATTER .get().format(date);
            String month   = MONTH_FORMATTER.get().format(date);
            String day     = DAY_FORMATTER  .get().format(date);
            String time    = TIME_FORMATTER .get().format(date);
            String content = diray.getContent();
            UserDirayResp userDirayResp = UserDirayResp.builder()
                    .month(month)
                    .day(day)
                    .time(time)
                    .content(content)
                    .build();

            if (!map.containsKey(year)) {
                UserAllDirayResp userAllDirayResp = UserAllDirayResp.builder()
                        .year(year)
                        .userDirays(new ArrayList<>())
                        .build();
                map.put(year, userAllDirayResp);
            }
            map.get(year).getUserDirays().add(userDirayResp);
        }

        return new ArrayList<UserAllDirayResp>(map.values());
    }

}
