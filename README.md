# Project Viewer2021
## 更新日志
### 2022.3.13
- 更新springboot版本至2.5.10，解决springframework、jetty漏洞
- 使用Murphysec Code Scan进行代码扫描，确保代码无安全漏洞
## 功能
- 读取MS project文件，并能显示
- 修改MS project文件
- 基于java跨平台实现
## 技术实现要点
### Project文件读写组件
- [mpxj v8.3.5](http://www.mpxj.org)
### 甘特图展示
- 使用基于miniui甘特图展现，参考[普加甘特图](http://www.plusgantt.com/)
### 后台处理技术
- springboot
- freemaker
- javafx
### 使用openjdk8或jdk8以上版本的需要使用集成openjfx的版本，JDK使用AZUL1.8JFX
- [Linux下载地址](https://cdn.azul.com/zulu/bin/zulu8.60.0.21-ca-fx-jdk8.0.322-linux_x64.tar.gz)
- [mac Intel下载地址](https://cdn.azul.com/zulu/bin/zulu8.60.0.21-ca-fx-jdk8.0.322-macosx_x64.zip)
- [mac M1下载地址](https://cdn.azul.com/zulu/bin/zulu8.60.0.21-ca-fx-jdk8.0.322-macosx_aarch64.tar.gz)
- [Windows下载地址](https://cdn.azul.com/zulu/bin/zulu8.60.0.21-ca-fx-jdk8.0.322-win_x64.zip)
# 打包编译
## 使用javafx-maven-plugin打包编译
- 使用javafx-maven-plugin可以打包成脱离JDK的安装程序，支持mac/win，打包完成后可生成DMG和msi安装包。
## 基于win的打包
- 修改pom文件的图标项```<icon>${basedir}/src/main/resources/icons/winproj.ico</icon>```
- Windows环境安装Inno Setup 5和 wix311
- 设置环境变量```PATH=C:\Program Files\Inno Setup 5;C:\wix311```，让打包插件能找到安装文件
- 执行```mvn clean jfx:native```,在taret/jfx/native目录下自动生成基于inno和基于wix的安装文件
## 基于mac的打包
- 修改pom文件的图标项```<icon>${basedir}/src/main/resources/icons/winproj.icns</icon>```
- 执行```mvn clean jfx:native```,在taret/jfx/native目录下自动生成pkg文件，dmg文件会生成到临时文件中，需要找。


## 快速生成 Mac App icns 图标

- 准备一个 1024 * 1024 的png图片，假设名字为 pic.png
- 命令行 `$ mkdir tmp.iconset`，创建一个临时目录存放不同大小的图片
- 把原图片转为不同大小的图片，并放入上面的临时目录

```
# 全部拷贝到命令行回车执行，执行结束之后去tmp.iconset查看十张图片是否生成好
sips -z 16 16     pic.png --out tmp.iconset/icon_16x16.png
sips -z 32 32     pic.png --out tmp.iconset/icon_16x16@2x.png
sips -z 32 32     pic.png --out tmp.iconset/icon_32x32.png
sips -z 64 64     pic.png --out tmp.iconset/icon_32x32@2x.png
sips -z 128 128   pic.png --out tmp.iconset/icon_128x128.png
sips -z 256 256   pic.png --out tmp.iconset/icon_128x128@2x.png
sips -z 256 256   pic.png --out tmp.iconset/icon_256x256.png
sips -z 512 512   pic.png --out tmp.iconset/icon_256x256@2x.png
sips -z 512 512   pic.png --out tmp.iconset/icon_512x512.png
sips -z 1024 1024   pic.png --out tmp.iconset/icon_512x512@2x.png
```
- 通过iconutil生成icns文件 `$ iconutil -c icns tmp.iconset -o Icon.icns`
