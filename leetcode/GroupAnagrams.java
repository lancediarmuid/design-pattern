import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 */
public class GroupAnagrams {
    /**
     * 异位词分组，有个特征即：还是原来的字母，但是排序不一样了
     * 又已知，每一个英文字母都有对应的 ASCII 码值，我们只需要将这些不太一样的 ASCII 码进行计算
     * 就可以得到一个词组特定的值，倘若计算之后的值相等，则将他们归为同一个分组当中
     */
    /**
     * 思路来自于哈希表中的哈希函数
     *
     * @param str 哈希函数
     * @return 计算之后的结果值
     */
    private Long hashString(String str) {
        int[] primeNum = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        char[] table = str.toCharArray();
        long product = 1L;
        for (char aTable : table) {
            product *= primeNum[aTable - 97];
        }
        return product;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<Long, List<String>> crucible = new HashMap<>();
        for (String param : strs) {
            Long hashValue = hashString(param);
            if (!crucible.keySet().contains(hashValue)){
                // 尚未存在
                crucible.put(hashValue, new ArrayList<>());
            }
            crucible.get(hashValue).add(param);
        }

        for (Long key : crucible.keySet()) {
            result.add(crucible.get(key));
        }
        return result;
    }

    public static void main(String[] args) {
        String[] parmas = {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"};

        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> result = groupAnagrams.groupAnagrams(parmas);
        System.out.println();
//        int n = 26;
//        int count = 0;
//        for(int i=2;i<=1000;i++) {
//            int flag = 0;//用作标记，如果是质数就为0，不是质数就为1
//            for(int j=2;j<i;j++){
//                if(i % j == 0 ){
//                    flag = 1;//不是质数，改为1
//                }
//            }
//            if(flag == 0){
//                count++;
//                System.out.printf("%d, ", i);
//                if (count == n){
//                    break;
//                }
//            }
//        }
    }
}
