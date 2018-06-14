package example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class One {
  String message = "foo";

  // 线程数
  private int threadCount = 5;

  // 锁
  CountDownLatch cdl;

  // 选定的标准
  private String[] standard;

  public int getThreadCount() {
    return threadCount;
  }

  public void setThreadCount(int threadCount) {
    this.threadCount = threadCount;
  }

  List<String> inputList = new ArrayList<>();

  List<String[]> outputList = new ArrayList<>();

  List<String> result = new ArrayList<>();

  public One() {}

  public String foo() {
    cdl = new CountDownLatch(threadCount);
    readFile();
    subList();
    try {
      cdl.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < outputList.size(); i++) {
      String[] strings = outputList.get(i);
      for (int j = 0; j < strings.length; j++) {
        result.add(strings[j]);
      }
    }

    writeFile();

    return message;
  }

  private void writeFile() {
    System.out.println();
    try (PrintWriter pw = new PrintWriter(new FileWriter("out.txt"))) {
      for (int i = 0; i < result.size(); i++) {
        pw.println(result.get(i));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void subList() {
    if (inputList.size() == 0)
      return;
    if (threadCount >= inputList.size())
      threadCount = inputList.size();

    standard = new String[threadCount - 1];

    for (int i = 0; i < threadCount - 1; i++) {
      standard[i] = inputList.get(i);
    }

    quickSort(standard);

    List<List<String>> subLists = new ArrayList<>();

    for (int i = 0; i < threadCount; i++) {
      subLists.add(new ArrayList<>());
    }

    for (int i = 0; i < inputList.size(); i++) {
      boolean isput = false;
      for (int j = 0; j < standard.length; j++) {

        if (inputList.get(i).compareTo(standard[j]) <= 0) {
          subLists.get(j).add(inputList.get(i));
          isput = true;
          break;
        }
      }
      if (!isput)
        subLists.get(threadCount - 1).add(inputList.get(i));
    }

    for (int i = 0; i < threadCount; i++) {
      List<String> list = subLists.get(i);
      String[] strings = new String[list.size()];
      list.toArray(strings);
      outputList.add(strings);
    }

    for (int i = 0; i < threadCount; i++) {
      String[] strings = outputList.get(i);
      new Thread(() -> {
        quickSort(strings);
        cdl.countDown();
      }).start();
    }
  }

  private void readFile() {
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")))) {
      String line;
      while ((line = br.readLine()) != null) {
        inputList.add(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void quickSort(String[] arrays) {
    subQuickSort(arrays, 0, arrays.length - 1);
  }

  private void subQuickSort(String[] arrays, int start, int end) {
    if (start >= end) {
      return;
    }
    int middleIndex = subQuickSortCore(arrays, start, end);
    subQuickSort(arrays, start, middleIndex - 1);
    subQuickSort(arrays, middleIndex + 1, end);
  }

  private int subQuickSortCore(String[] arrays, int start, int end) {
    String middleValue = arrays[start];
    while (start < end) {
      while (arrays[end].compareTo(middleValue) >= 0 && start < end) {
        end--;
      }
      arrays[start] = arrays[end];
      while (arrays[start].compareTo(middleValue) <= 0 && start < end) {
        start++;
      }
      arrays[end] = arrays[start];
    }
    arrays[start] = middleValue;
    return start;
  }
}
