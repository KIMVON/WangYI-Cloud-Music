# WangYI-Cloud-Music

本项目主要是用我个人的练手，学习如何去用MediaPlayer和MVP+Retrofit+Rxjava

我用的是Google的mvp，在网上基本上没有相同的，但是主要是思想上相同的，所以没有什么关系。一开始我以为MediaPlayer只是可以加载本地的下载好的音乐，但是后来查阅了资料，发现原来它也可以解析Music的URL的，直接播放

后来到了一个重点难点就是：
MVP+Retrofit+Rxjava
用的最长的时间就是Retrofit+Rxjava，首先要了解它们两个的个字用法，入门算是比较久了，不过好彩网上有很多大牛提供了参考。
我个人觉得写的最好的Rxjava网子，没有之一，可能我孤陋寡闻把：
http://gank.io/post/560e15be2dca930e00da1083

Retrofit我觉得都差不多，最好去郭林的公众号里面有一篇写Retrofit的，写的是不错的，也可以去看看，里面也有Retrofit+Rxjava、MVP+Retrofit+Rxjava，虽然和我的不太一样，但是差不多的，要知道编程这个东西，重要的是学习思想。

之前运用的时候一直想用，Google有一个地方设置的很好，就是model层的三层
——cache
——local
——remote

就是三级缓存：
首先是从cache里面查找有没有
然后如果没有就在本地服务器或者本地存储找
如果再没有就再网络上找

Google是这样设置的（加上我个人的理解）
model
——source
————localDataSource
————remoteDataSource
————AppRespository
————DataSource（interface）  
——bean

再cache方面我也再网上稍微看了
再Google Sample 的Github放出来的例子中是用
Map cache = new LinkedHashMap（）<>；（好像是LinkedMap，忘记了，反正大概是这个样子的），但是网上很多资料和Google官方推荐是LruCache的，其实后来了解了LruCache的实现原理也是用Map上边例子说的那样实现的，只不过LruCache框架已经封装的很好了，有回收的，Lru是用的最少的就被回收算法。用法也是和Map差不多的。好像还有一个比较好的Cache框架ASimpleCache，比较轻量的，反正我目前还没有用过。



其实Google的MVP和其他的MVP还有一个不同的是，google是把每个页面分开一个包
比如这样：播放音乐页面
——playMusicSection
————PlayMusicActivity
————PlayMusicFragment
————PlayMusicPresenter
————PlayMusicContract
算是一种变形把，把Activity作为总指挥，Fragment作为View，Presenter作为Presenter，然后多了一个Contract，这个我觉得很有用，比如再开发项目是，你可以先把每个页面Contract想好实现好，然后再去实现具体的，这样无疑会加快开发的效率

而传统网上我所见的就是Presenter一个文件夹，View一个文件夹，Model一个文件夹，还有其他的自行去网上查看

我个人觉得Google这样好一点，看起来一目了然


然后是布局也是一种新的挑战，在播放音乐的动画，也让我巩固了属性动画，和amin文件夹和amintor文件夹的区别，其中animator是属性动画的文件夹，同时也学习了在XML里面设置动画

再View上面我了解了SeekBar，是封装了ProgressBar的，多用于播放视频音乐的进度条，它可以拖拽哦

再属性动画上，原本我一直以为自己对于基本的属性动画已经了解的差不多了，但是实践起来，觉得自己还不够火候，要多时间，比如对于旋转rotation的旋转中心，再属性动画里面如何去设置呢，项目中使用在XML里面设置，通过translaProviteX、translaProviteY，英文错了，大概是这个样子的

其实对于XML的布局我觉得自己还有很多东西要学的，之前做的布局太简单了，以为没有什么问题，现在接触了一些难的，觉得如果自己原创出来，肯定没有它做的那么好，很多细节都不太会，慢慢学吧


还有一个很尴尬的问题，一直不知道项目中的Service在哪里调用，在Presenter层调用呢？还是在Fragment层调用呢？（看来对MVP的理解还有待提高），后来在群上问了一些厉害的人，它给我解释了一番（其实只是短短是一两句话），后来不想说了，直接丢个视频给我，我蛮感谢的，虽然视频中根本没有讲到这个问题，后来我懂了它的意思，是在Presenter里面调用，即使处理Service的返回
但是有个难点就是调用Service的Context如何弄到手呢？
后来尝试了不同的方法，我选择通过Application.getContext获取（自己弄了一个Application类获取），然后就可以调用了