package com.yy.crm.utils;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author luyuanyuan on 2018/1/17.
 */
@Slf4j
public class YYUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] num = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(System.currentTimeMillis());
        boolean flag = false;
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }

    public static List<Integer> splitToListInt(String str) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        return strList.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static boolean constantTimeCompare(String a, String b) {
        return MessageDigest.isEqual(a.getBytes(), b.getBytes());
    }

    public static void main(String[] args) {
        //System.out.println(randomPassword());
        //Thread.sleep(100);
        //System.out.println(randomPassword());
        //Thread.sleep(100);
        //System.out.println(randomPassword());
        //System.out.println(genHMAC("{\"ref\":\"refs/heads/master\",\"before\":\"39c822f9168913301de0de04053bb1edb294baa5\",\"after\":\"3e98ec27df4dd9cfada8120a6e52eb5caab899bd\",\"created\":false,\"deleted\":false,\"forced\":false,\"base_ref\":null,\"compare\":\"https://github.com/luyuanx2/crm/compare/39c822f91689...3e98ec27df4d\",\"commits\":[{\"id\":\"3e98ec27df4dd9cfada8120a6e52eb5caab899bd\",\"tree_id\":\"d4d162c67d60dd2b60e117f37d6b0b07d63af6eb\",\"distinct\":true,\"message\":\"提交\",\"timestamp\":\"2018-03-02T20:39:55+08:00\",\"url\":\"https://github.com/luyuanx2/crm/commit/3e98ec27df4dd9cfada8120a6e52eb5caab899bd\",\"author\":{\"name\":\"luyuanyuan\",\"email\":\"694436921@qq.com\",\"username\":\"luyuanx2\"},\"committer\":{\"name\":\"luyuanyuan\",\"email\":\"694436921@qq.com\",\"username\":\"luyuanx2\"},\"added\":[],\"removed\":[],\"modified\":[\"crm-manage/src/main/java/com/yy/crm/manage/controller/CommonController.java\"]}],\"head_commit\":{\"id\":\"3e98ec27df4dd9cfada8120a6e52eb5caab899bd\",\"tree_id\":\"d4d162c67d60dd2b60e117f37d6b0b07d63af6eb\",\"distinct\":true,\"message\":\"提交\",\"timestamp\":\"2018-03-02T20:39:55+08:00\",\"url\":\"https://github.com/luyuanx2/crm/commit/3e98ec27df4dd9cfada8120a6e52eb5caab899bd\",\"author\":{\"name\":\"luyuanyuan\",\"email\":\"694436921@qq.com\",\"username\":\"luyuanx2\"},\"committer\":{\"name\":\"luyuanyuan\",\"email\":\"694436921@qq.com\",\"username\":\"luyuanx2\"},\"added\":[],\"removed\":[],\"modified\":[\"crm-manage/src/main/java/com/yy/crm/manage/controller/CommonController.java\"]},\"repository\":{\"id\":115932213,\"name\":\"crm\",\"full_name\":\"luyuanx2/crm\",\"owner\":{\"name\":\"luyuanx2\",\"email\":\"694436921@qq.com\",\"login\":\"luyuanx2\",\"id\":26129033,\"avatar_url\":\"https://avatars1.githubusercontent.com/u/26129033?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/luyuanx2\",\"html_url\":\"https://github.com/luyuanx2\",\"followers_url\":\"https://api.github.com/users/luyuanx2/followers\",\"following_url\":\"https://api.github.com/users/luyuanx2/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/luyuanx2/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/luyuanx2/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/luyuanx2/subscriptions\",\"organizations_url\":\"https://api.github.com/users/luyuanx2/orgs\",\"repos_url\":\"https://api.github.com/users/luyuanx2/repos\",\"events_url\":\"https://api.github.com/users/luyuanx2/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/luyuanx2/received_events\",\"type\":\"User\",\"site_admin\":false},\"private\":false,\"html_url\":\"https://github.com/luyuanx2/crm\",\"description\":null,\"fork\":false,\"url\":\"https://github.com/luyuanx2/crm\",\"forks_url\":\"https://api.github.com/repos/luyuanx2/crm/forks\",\"keys_url\":\"https://api.github.com/repos/luyuanx2/crm/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/luyuanx2/crm/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/luyuanx2/crm/teams\",\"hooks_url\":\"https://api.github.com/repos/luyuanx2/crm/hooks\",\"issue_events_url\":\"https://api.github.com/repos/luyuanx2/crm/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/luyuanx2/crm/events\",\"assignees_url\":\"https://api.github.com/repos/luyuanx2/crm/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/luyuanx2/crm/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/luyuanx2/crm/tags\",\"blobs_url\":\"https://api.github.com/repos/luyuanx2/crm/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/luyuanx2/crm/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/luyuanx2/crm/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/luyuanx2/crm/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/luyuanx2/crm/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/luyuanx2/crm/languages\",\"stargazers_url\":\"https://api.github.com/repos/luyuanx2/crm/stargazers\",\"contributors_url\":\"https://api.github.com/repos/luyuanx2/crm/contributors\",\"subscribers_url\":\"https://api.github.com/repos/luyuanx2/crm/subscribers\",\"subscription_url\":\"https://api.github.com/repos/luyuanx2/crm/subscription\",\"commits_url\":\"https://api.github.com/repos/luyuanx2/crm/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/luyuanx2/crm/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/luyuanx2/crm/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/luyuanx2/crm/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/luyuanx2/crm/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/luyuanx2/crm/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/luyuanx2/crm/merges\",\"archive_url\":\"https://api.github.com/repos/luyuanx2/crm/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/luyuanx2/crm/downloads\",\"issues_url\":\"https://api.github.com/repos/luyuanx2/crm/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/luyuanx2/crm/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/luyuanx2/crm/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/luyuanx2/crm/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/luyuanx2/crm/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/luyuanx2/crm/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/luyuanx2/crm/deployments\",\"created_at\":1514825271,\"updated_at\":\"2018-01-02T10:49:33Z\",\"pushed_at\":1519994416,\"git_url\":\"git://github.com/luyuanx2/crm.git\",\"ssh_url\":\"git@github.com:luyuanx2/crm.git\",\"clone_url\":\"https://github.com/luyuanx2/crm.git\",\"svn_url\":\"https://github.com/luyuanx2/crm\",\"homepage\":null,\"size\":314,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"Java\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"archived\":false,\"open_issues_count\":0,\"license\":null,\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"master\",\"stargazers\":0,\"master_branch\":\"master\"},\"pusher\":{\"name\":\"luyuanx2\",\"email\":\"694436921@qq.com\"},\"sender\":{\"login\":\"luyuanx2\",\"id\":26129033,\"avatar_url\":\"https://avatars1.githubusercontent.com/u/26129033?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/luyuanx2\",\"html_url\":\"https://github.com/luyuanx2\",\"followers_url\":\"https://api.github.com/users/luyuanx2/followers\",\"following_url\":\"https://api.github.com/users/luyuanx2/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/luyuanx2/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/luyuanx2/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/luyuanx2/subscriptions\",\"organizations_url\":\"https://api.github.com/users/luyuanx2/orgs\",\"repos_url\":\"https://api.github.com/users/luyuanx2/repos\",\"events_url\":\"https://api.github.com/users/luyuanx2/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/luyuanx2/received_events\",\"type\":\"User\",\"site_admin\":false}}","lyy"));
    }
}
