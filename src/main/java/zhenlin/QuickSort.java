package zhenlin;

/**
 * 快速排序是其基本思想是基本思想是，通过一趟排序将待排记录分隔成独立的两部分， 其中一部分记录的关键字均比另一部分的关键字小， 则可分别对这两部分记录继续进行排序，以达到整个序列有序。 步驟：
 * 1、从数列中挑出一个元素，称为 "基准"（pivot）， 2、重新排序数列，所有元素比基准值小的摆放在基准前面， 所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，
 * 该基准就处于数列的中间位置。这个称为分区（partition）操作。 3、递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * 
 * @author Administrator
 *
 */
public class QuickSort {

  /**
   * 
   * @param a 待排序数组
   * @param low 可以看做低位助手
   * @param high 可以看做高位助手 低助手是用来找比基准位大的数 高助手是用来找比基准位小的数 这样就可以看做两个助手在活动
   */
  void quickSort(String[] a, int low, int high) {
    int start = low;// 起始位置 0；
    int end = high; // 结束位置
    String base = a[low]; // 基准数 ：一般是第一位
    int tempIndex = low; // 找到的符合要求的位置：因为要把它的值付给基准数所在位置 所以要记录该位置 可以看做是助手移动到的位置
    while (low < high) {
      // 高助手从右向左找一个比基准位小的数 找到后给到低助手当前所在位置
      // 没有找到的话 高助手向前移动一位
      while (low < high && base.compareTo(a[high]) <= 0) {
        high--;
      }
      // 找到时 把找到的数赋值给低助手所在位置
      a[low] = a[high];
      tempIndex = high;// 记录当前助手位置

      // 然后低助手从左向右找一个比基准数大的数 ，找到后给到高助手当前所在位置
      // 没有找到的话 低助手向后移动一位
      while (low < high && base.compareTo(a[low]) >= 0) {
        low++;
      }
      // 找到后赋值给高助手当前所在位置
      a[high] = a[low];
      tempIndex = low;// 记录当前助手位置

      // 直到循环结束 -->低助手和高助手重叠 就把基准数赋到当前中轴重叠位置
      a[tempIndex] = base;

    }
    // 以上第一次排序结束 把数列分成了前后两个部分
    // 最后在对上面前后两个部分数列 分别递归
    if (low - start > 1) {// 前部分至少有两个数据
      quickSort(a, 0, low - 1);
    }
    if (end - high > 1) {
      quickSort(a, high + 1, end);
    }
  }

  public static void main(String[] args) {
    QuickSort q = new QuickSort();
    String str [] = {"q","a","e","d","b","c"};
    q.quickSort(str, 0, str.length - 1);
    for(int i =0 ;i < str.length; i++){    
      System.out.println(i + " ------ " + str[i]);
    }
  }

}
