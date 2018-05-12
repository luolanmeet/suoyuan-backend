package emotion;

import org.junit.Test;

import com.sy.emotion.judge.impl.EmotionJudge;
import com.sy.word.segmentation.impl.MaximumMatching;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmotionJudgeTest {
    
    EmotionJudge judge = new EmotionJudge();
    MaximumMatching m = new MaximumMatching();
    
    String str1 = "想您。自您走后，每到生日前后，都会在无人的角落偷偷流泪。委屈，难过，埋怨，不舍，想念，遗憾……这些感情还和七年前一样。有增无减。七年了。妈妈，我好想您。也许，只有来生才能再见到您，我还做您的女儿。";
    String str2 = "习惯真的太可怕了。不见得是喜欢，更不用提爱情。这两天除了睡觉吃饭，剩下的时间都在想你在哪里。为什么突然就冷淡了，为什么变成这样了？都说金牛很酷，你不找我，我又为什么要主动找你。我也想就这么酷啊，可是怕我们就这么到此为止了。坏人。";
    String str3 = "今天是继续和内在小孩连结的一天，为了唤醒自己爱的能力而努力。今天去奶奶家找小时候的照片，翻看着模糊掉的记忆，对比着亲人们的容颜，感叹着岁月催人老啊岁月不饶人。希望在前进中不停止脚步，一天比一天更爱，一天比一天更好，也算不辜负时间。感恩。";
    String str4 = "What is life？What kind of life i am looking for？Please let these questions knock me everyday everyday everyday！";
    String str5 = "今天如果能够把日记分析还有推送都写完，那就是非常棒的一天了。写完这个我就可以做社区功能还有写过几个分词的算法了。加油加油。";
    String str6 = "哈哈，我来测试情感分析功能啦，希望不要太糟糕。";
    
    @Test
    public void testJudge() {
        
        log.info("{}", judge.judge(m.seg(str1)));
        log.info("{}", judge.judge(m.seg(str2)));
        log.info("{}", judge.judge(m.seg(str3)));
        log.info("{}", judge.judge(m.seg(str4)));
        log.info("{}", judge.judge(m.seg(str5)));
        log.info("{}", judge.judge(m.seg(str6)));
    }
    
}
