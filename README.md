# 笔试题-min-coding-test
   ![image](https://github.com/eBusinessMan/mintest/blob/master/src/main/resources/question.png)
      <br/>
  # 总体算法思路
  题目要求输入一个整形数组, 计算出这个数组下, 能组合的字母字符串.<br>
  根据题目给出的样例, 我认为,场景中有以下情况没有明确说明:<br/>
  困惑点1. "各个字母都相同,位置不同的"是否被认为是相同的字符串组合? <br/>
  例如,诸如 "abc","cab","cba" 和 "bac"这类字母组合是否被认为是相同的字符串组合<br/><br/>
  困惑点2. "all possible letter combinations that the numbers could present"<br/>
  举例,用户输入一个整形数组{2,2,3}, 那么"all possible letter combinations"是否意味着{2},{2,2},{3},{2,3}对应的字母组合是否包括在内??       还是说只能是{2,2,3}这一种情况?<br/><br/>
  考虑到程序的可扩展性,我们的代码需要考虑兼容这中业务中的容易变化,不确定的点.<br/>
  <br/>
  在我的项目设计中,采用 策略模式 应对上述的"不确定的点", 见下文com.mory.biz.StatCombiner简介. <br/>
  但项目中,我仅仅以以下的认定提供代码实现说明:<br/>
     (A1)"各个字母都相同,位置不同的"被认为是相同的字符串组合, 故我的答案中会将此类重复的字母字符串去重.<br/>
     (A2)例如用户输入一个整形数组{2,2,3}, 那么"all possible letter combinations"包括{2},{2,2},{3},{2,3}对应的字母组合.<br/><br/>

  总体算法思路:<br/>
   * 1. 先找出用户输入的数字数组的所有子数组, <br/>
   * 2. 逐个对各子数组计算对应的所有的字母组合,并去重. <br/>
      
## 总体算法思路中的考虑点  
  * 用户输入的整形数组需要考虑以下情况:
    * 数组中可能包含重复数字<br>
    * 数组中可能包含不在[2,9]之间的数字<br>
    <br/>
    我们需要对输入的数组计算其所有的子数组.<br/>
    tips:每个子数组会转化成com.mory.DigitCompose类实例(见下文讲解)<br/>
       
  * 求解用户输入的数组的子数组时, 需要将重复的数字组合去重. 因为重复的子数组对应的字母组合是相同的<br>
    如{1,2}, {2,1}属于重复的数字组合, 需要去重. <br/>
     样例:<br/>
      inputs={1,2,3,2}的所有不重复的子数组如下:<br/>
      { 1, } <br/>
      { 2, } <br/>
      { 3, } <br/>
      { 1,2, } <br/>
      { 1,3, } <br/>
      { 2,2, } <br/>
      { 2,3, } <br/>
      { 1,2,3, } <br/>
      { 1,2,2, } <br/>
      { 2,2,3, } <br/>
      { 1,2,2,3  } <br/>
      其中{2,3}, {3,2}这种重复性的子数组已经去重  <br/>

    计算所有子数组并去重的方法, <br/>
    原理过程-举例说明:<br/>
        设用户输入inputs = {1,2,3}, HashSet变量digitComposeSet:<br/>
        a. 将"1"(封装入DigitCompose对象)放入digitComposeSet中,<br/>
        b. 将"2"与digitComposeSet中所有已有元素,目前是"1", 组拼成"1,2"(并封装入DigitCompose对象)放入digitComposeSet中, 再将"2"(封装入DigitCompose对象)放入digitComposeSet中,<br/>
        c. 将"3"与digitComposeSet中所有已有元素,目前是{"1","1,2","2"}, 组拼成"1,3","1,2,3","2,3"(并封装入DigitCompose对象)放入digitComposeSet中, 再将"3"放入digitComposeSet中,<br/>
        此时digitComposeSet={"1","1,2", "2", "1,3","1,2,3","2,3", "3"}. <br/>
        tips: 由于DigitCompose的特点(见下文DigitCompose环节讲解), 重复的子数组(对应的DigitCompose对象)会在存入digitComposeSet去重.<br/><br/>
     
  * 逐个子数组计算其对应的字母组合
      * 有个注意点, 当子数组中包含不在[2,9]之间的数字时, 其实此子数组和"去掉不在[2,9]之间的数字后的"另一个子数组的字母组合一致的 <br/><br/>
      举例, 上述inputs={1,2,3,2}的所有不重复的子数组中, 由于数字1并没有映射的字母集合(见题目的按键button), 所以:<br>
      {1,2}的字母组合其实和{2}的一致 <br/>
      {1,3}的字母组合其实和{3}的一致 <br/>
      {1,2,3}的字母组合其实和{2,3}的一致 <br/>
      {1,2,2,}的字母组合其实和{2,2}的一致 <br/>
      {1,2,2,3}的字母组合其实和{2,2,3}的一致 <br/>
      因此, 我们对于"包含不在[2,9]之间的数字"的子数组可以直接不处理. 
      <br/>
      
      * 这个环节需要考虑对重复的字母组合进行去重. <br/>
      求{ 3,3,4}的字符组合: <br/>
      3 对应字符  {D,E,F}, <br/>
      3 对应字符  {D,E,F}, <br/>
      4 对应字符 {G,H,I}, <br/>
      可得字母组合:<br/>
      {<br/>
      DDG, DDH, DDI, <br/>
      DEG, DEH, DEI, <br/>
      DFG, DFH, DFI, <br/>
      <br/>
      EDG, EDH, EDI, <br/>
      EEG, EEH, EEI, <br/>
      EFG, EFH, EFI, <br/>
      <br/>
      FDG, FDH, FDI, <br/>
      FEG, FEH, FEI, <br/>
      FFG, FFH, FFI, <br/>
      }<br/>
      可见上述也存在重复组合, 如DEG, EDG等等之类字母组合也是需要去重的.<br/>
      去重的方法是:将字母组合char[]从小到大排序, 然后重新生成String(char[])对象, 再放入HashSet<String>中即可去重.<br/>

## 运行示意图  
    本答案以一个基于控制台的交互型程序呈现:
  * 环境准备: jdk1.8
  * 由于在工程中采用lombok包,所以IDE(eclipse/Ideaj)需要安装lombok插件.
  * 导入工程后, run/debug执行com.mory.BootCubeApplication#main按照提示操作即可.<br/>
    <br/>
  如下图举例:<br/>
    console会先打印出每个不重复的子数组以及其下的所有不重复的字母组合: <br/> 
   ![image](https://github.com/eBusinessMan/mintest/blob/master/src/main/resources/runtimeConsole.png)
      <br/>
  
# 工程代码分析
  ### 数字子数组包装类--com.mory.DigitCompose <br/>
    两个重要的成员field:<br/>
    com.mory.DigitCompose#digits<br/>
        每个数字子数组会按照从小到大排序后,再被按照"数字,数字,...,数字"的格式组拼成字符串digits <br/>
    com.mory.DigitCompose#containByondLetter<br/>
        如果此子数祖中包含"不在[2,9]之间的数字", 那么在new实例时构造方法中会判断    <br/>
  由于本测试题场景中需要"对重复的子数组去重",即成员属性digits相等(equals)的不同DigitCompose实例相等(equals). <br/>
  本答案中是通过HashSet<DigitCompose>的方式实现去重, 故同时重写了DigitCompose的hashCode(), equals()方法.额外地, 也重写了, toString()方法.
   
  ### 统计总启动类--com.mory.biz.StatCombiner <br/>
    两个重要的成员field:<br/>
    //数字组合计算器<br/>
    com.mory.biz.StatCombiner#digitsComposeCalculator  <br/>
    //字母组合计算器<br/>
    com.mory.biz.StatCombiner#lettersComposeCalculator  <br/>
  StatCombiner是统计启动类, 采用 模板方法模式 写好了总的逻辑过程(见方法com.mory.biz.StatCombiner#startCount()).<br/>
  根据文头的"困惑点", 分别创建对应"困惑点"场景的digitsComposeCalculator对象和lettersComposeCalculator对象, 传入StatCombiner对象中,即可满足需要定制.<br/>
  
  ### 用户输入的数字数组的子数组计算器接口--com.mory.interfaces.DigitsComposeCalculator <br/>
    采用接口的形式, 用户可以根据自己的需要按照场景需要定制不同的数字组合算法.
    tips: 在本项目中, 我提供了一个实现类, 见com.mory.interfaces.impl.MyDigitsComposeCalculator, 会对重复的子数字组合进行去重!
  
  ### 字母组合计算器接口--com.mory.interfaces.LettersComposeCalculator <br/>
    采用接口的形式, 用户可以根据自己的需要按照场景需要定制不同的字母组合算法.
    tips: 在本项目中, 我按照文头的认定"(A1)"各个字母都相同,位置不同的"被认为是相同的字符串组合, 故我的答案中会将此类重复的字母字符串去重", 提供了一个实现类, 见com.mory.interfaces.impl.MyParallelLettersComposeCalculator, <br/>
    tips: <br/>
    MyParallelLettersComposeCalculator 继承自 ParallelLettersComposeCalculator, 是一个抽象的平行"字母组合"计算器 . <br/>
    考虑到用户输入的数字数组可能非常长, 那么对应的数字子数组会非常多, 所以ParallelLettersComposeCalculator内置线程池, 可以并行地对各个数字子数组计算其对应的字母组合, 

  
  
  
  
  
  

  
  
 
