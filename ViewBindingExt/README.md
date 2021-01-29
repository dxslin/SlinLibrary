
 # ViewBinding扩展库，方便快速创建binding



 使用步骤：
 ### 1. 添加依赖项
 根目录build.gradle添加仓库https://jitpack.io：
 ```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
 ```
 ```groovy
 //添加ViewBindingExt依赖项
 implementation "com.github.dxslin.SlinLibrary:ViewBindingExt:1.0.0"
 ```

 ### 2. 在Activity里面使用
 #### 方式一：
直接在Activity里面调用
 ```kotlin

    private val bind:ActivityXXXBinding by viewBinding()

 ```

 #### 方式二：
 创建BaseActivity，继承BaseActivity之后就可以直接使用binding

 BaseActivity.kt
 ```kotlin
     open class BaseActivity<VB : ViewBinding> : Activity() {

         protected val binding by ActivityBindingDelegate<VB>()


     }
 ```
MainActivity.kt
 ```kotlin
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvHelloWorld.text = "xxx"
    }
}
 ```
 **注：因为上面是通过委托懒加载的，如果没有调用binding，就不会创建实例，即不会为Activity设置ContentView，可能会导致页面显示空白**

### 3. 在Fragment里面使用
 #### 方式一：
直接在Fragment里面调用
 ```kotlin

    private val bind:FragmentXXXBinding by viewBinding()

 ```

 #### 方式二：
 创建BaseFragment，继承BaseFragment之后就可以直接使用binding

 BaseFragment.kt
 ```kotlin
    open class BaseFragment<VB : ViewBinding>(@LayoutRes layoutId: Int) : Fragment(layoutId) {

        protected val binding: VB by FragmentBindingDelegate()

    }
 ```
 **注：这里Fragment调用的是Fragment(layoutId)构造函数，需要传入布局id的**
 主要是因为`XXViewBinding.inflate` 方法还要传 parent 对象就不好处理，所以我们调用的是 `XXViewBinding.bind`，
 只需传个 [View]，在 [Fragment] 很好拿，因此需要默认传入`layoutId`让Fragment自己创建View

TestFragment.kt
 ```kotlin
class TestFragment : BaseFragment<FragmentTestBinding>(R.layout.fragment_test) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHelloWorld.text = "xxx"

    }
}
 ```


### 参考文档
[ViewBindingKtx: https://github.com/DylanCaiCoding/ViewBindingKtx.git](https://github.com/DylanCaiCoding/ViewBindingKtx.git)



