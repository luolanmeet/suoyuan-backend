package word;

import org.junit.Test;

import com.sy.word.segmentation.impl.MaximumMatching;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaximumMatchingTest {

    MaximumMatching m = new MaximumMatching();
    
    String str1 = "想您。自您走后，每到生日前后，都会在无人的角落偷偷流泪。委屈，难过，埋怨，不舍，想念，遗憾……这些感情还和七年前一样。有增无减。七年了。妈妈，我好想您。也许，只有来生才能再见到您，我还做您的女儿。";
    String str2 = "习惯真的太可怕了。不见得是喜欢，更不用提爱情。这两天除了睡觉吃饭，剩下的时间都在想你在哪里。为什么突然就冷淡了，为什么变成这样了？都说金牛很酷，你不找我，我又为什么要主动找你。我也想就这么酷啊，可是怕我们就这么到此为止了。坏人。";
    String str3 = "What is life？What kind of life i am looking for？Please let these questions knock me everyday everyday everyday！";
    
    @Test
    public void testSeg() {
        log.info("{}", m.seg(str1));
        log.info("{}", m.seg(str2));
        log.info("{}", m.seg(str3));
    }

}
