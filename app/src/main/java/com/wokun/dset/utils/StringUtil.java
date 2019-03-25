package com.wokun.dset.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;


import com.wokun.dset.utils.MD5.Md5Encrypt;
import com.wokun.dset.utils.MD5.ParameterUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class StringUtil {

    /**
     * 保存图片到本地的方法
     *
     * @param context 上下文
     * @param bmp     图片
     * @return 图片存放地址
     */
    public static String saveImageToGallerys(Context context, Bitmap bmp) {

        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        String path = Environment.getExternalStorageDirectory() + "/Boohee/" + fileName;
        Log.d("czb", "图片存放地址============" + path);
        return path;

    }

    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static String getOldImei(String newImei, String oldImei) {
        if (!"".equals(oldImei)) {
            String[] str = oldImei.split(",");
            if (str.length == 5) {
                oldImei = oldImei.substring(0, oldImei.lastIndexOf(","));
                newImei = newImei + "," + oldImei;
            } else {
                newImei = newImei + "," + oldImei;
            }
        }
        return newImei;
    }

    public static String getString(String str, int lenght) {
        int strLenght = str.length();
        String str1 = "";
        if (strLenght == lenght) {
            int strInt = Integer.valueOf(str);
            if ("999999".equals(str)) {
                str1 = "1";
            } else {
                str1 = String.valueOf(strInt + 1);
            }
        } else {
            if (!"0".equals(str)) {
                String newStr = str.replaceFirst("^0*", "");
                int strInt = Integer.valueOf(newStr);
                str1 = String.valueOf(strInt + 1);
            } else {
                str1 = "1";
            }
        }
        String str2 = "";
        for (int i = 0; i < (lenght - str1.length()); i++) {
            str2 = str2 + "0";
        }
        str2 = str2 + str1;
        return str2;
    }

    public static String getAmount(String amount, int lenght) {
        String str2 = "";
        for (int i = 0; i < (lenght - amount.length()); i++) {
            str2 = str2 + "0";
        }
        str2 = str2 + amount;
        return str2;
    }

    public static String getZero(String str, int lenght) {
        String str2 = "";
        for (int i = 0; i < (lenght - str.length()); i++) {
            str2 = str2 + "0";
        }
        str2 = str + str2;
        return str2;
    }

    public static String getRidOfZero(String str) {
        String newStr = str.replaceFirst("^0*", "");
        return newStr;
    }

    public static String getSpace(String str, int lenght) {
        String str2 = "";
        for (int i = 0; i < (lenght - str.length()); i++) {
            str2 = str2 + " ";
        }
        str2 = str + str2;
        return str2;
    }

    //得到加密的工作密钥
    public static String getMackkeyAndPinkey(String value) {
        return value.substring(0, 16);
    }

    //得到二维码或条形码字符串
    public static String getwString(String value) {
        return value.substring(7);
    }

    public static Map<String, String> getResult(String value, String str) {
        Map<String, String> map = new HashMap<String, String>();
        String[] sp = value.split("FF03");
        map.put("orderNo", sp[0].substring(7));
        if (str.equals("004")) {
            String[] sp1 = sp[1].split("FF12");
            map.put("result", sp1[0].substring(3));
        } else {
            String[] sp1 = sp[1].split("FF07");
            map.put("result", sp1[0].substring(3));
        }
        return map;
    }

    public static Map<String, String> getResult1(String value, String str) {
        Map<String, String> map = new HashMap<String, String>();
        /*if(str.equals("004")){
            String[] sp1=value.split("FF12");
			map.put("result",sp1[0].substring(7));
		}else{
			String[] sp1=value.split("FF07");
			map.put("result",sp1[0].substring(7));
		}*/
        int lenght = value.indexOf("FF01");

        String str1 = value.substring(lenght);
        String str2 = str1.substring(4, 7);
        int le = 0;
        if (str2.substring(0, 1).equals("0")) {
            le = Integer.valueOf(str2.substring(1));
        } else {
            le = Integer.valueOf(str2);
        }
        map.put("orderNo", str1.substring(7, 7 + le));
        return map;
    }

    public static String getResult2(String value, String str) {
        int lenght = value.indexOf(str);
        String str1 = value.substring(lenght);
        String str2 = str1.substring(4, 7);
        int le = Integer.valueOf(str2);
        return str1.substring(7, 7 + le);
    }

    public static String Md5Str(Map<String, String> resultMap, String key) {
        String responseString = ParameterUtils.getRequestQueryString(resultMap, key);
        String sign = Md5Encrypt.md5(responseString);
        return sign;
    }


    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String getCurrentTime() {
//		int a[] = new int[10];
//		String s = "";
//		for (int i = 0; i < a.length; i++) {
//			a[i] = (int) (10 * (Math.random()));
//			s = s + a[i];
//		}
//		return s;
        String time = String.valueOf(System.currentTimeMillis()/1000);
        return time;
    }

    /**
     * 获取随机数
     *
     * @return
     */
/*
    public static String random() {
        String s = null;
        String str = null;
        try {
            str = DateUtils.getCurrDateByBjtime("yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
        s = DateUtils.timeToStamp(str, "yyyy-MM-dd HH:mm:ss");
        return s;
    }
*/

    public static String getFromtNumber(double divisor, double dividend) {
        DecimalFormat df2 = new DecimalFormat("###.00");
        String result = df2.format(divisor / dividend);
        if (result.substring(0, 1).equals(".")) {
            result = "0" + result;
        }
        return result;
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat  sf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return sf.format(d);
    }



    public static String generateCheckValue(byte[] src) {
        System.out.println("src : " + src.toString());
        byte b = 0x00;
        for (int i = 0; i < src.length; i++) {
            b = (byte) (b ^ src[i]);
        }

        String byte2hex = byte2hex(new byte[]{b});
        System.out.println("byte2hex : " + byte2hex);
        return byte2hex;
    }

    /**
     * 二进制转字符串
     *
     * @param src
     * @return
     */
    public static String byte2hex(byte[] src) {
        final String HEX = "0123456789abcdef";
        StringBuilder sb = new StringBuilder(src.length * 2);
        for (byte b : src) {
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            sb.append(HEX.charAt(b & 0x0f));
        }
        return sb.toString();
    }

    /**
     * 文件转字节
     *
     * @param file
     * @return
     */
    public static byte[] File2Bytes(File file) {
        int byte_size = 1024;
        byte[] b = new byte[byte_size];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
                    byte_size);
            for (int length; (length = fileInputStream.read(b)) != -1; ) {
                outputStream.write(b, 0, length);
            }
            fileInputStream.close();
            outputStream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取系统时间 年月日
     *
     * @return 日期
     */
    public static String getDateDay() {
        String year, month, day;
        Calendar c = Calendar.getInstance();
        year = String.valueOf(c.get(Calendar.YEAR));//年
        month = String.valueOf(c.get(Calendar.MONTH));//月
        day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));//日
        return day;
    }

    /**
     * 获取系统时间 年月日
     *
     * @return 日期
     */
    public static String getDate() {
        String year, month, day;
        Calendar c = Calendar.getInstance();
        year = String.valueOf(c.get(Calendar.YEAR));//年
        month = String.valueOf(c.get(Calendar.MONTH) + 1);//月
        day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));//日
        String date = year + "年" + month + "月" + day + "日";
        return date;
    }

//    /**
//     * @param content 生成二维码传递的内容
//     * @param width   二维码 宽
//     * @param height  二维码 高
//     * @return
//     */
//    public static Bitmap generateQRCodeBitmap(String content, int width, int height) {
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        Map<EncodeHintType, String> hints = new HashMap<>();
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//        try {
//            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//            int[] pixels = new int[width * height];
//            for (int i = 0; i < height; i++) {
//                for (int j = 0; j < width; j++) {
//                    if (encode.get(j, i)) {
//                        pixels[i * width + j] = 0x00000000;
//                    } else {
//                        pixels[i * width + j] = 0xffffffff;
//                    }
//                }
//            }
//            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 保存图片到本地的方法
     *
     * @param context 上下文
     * @param bmp     图片
     * @return 图片存放地址
     */
    public static String saveImageToGallery(Context context, Bitmap bmp, String fileName) {

        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "CBY");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
//        String fileName = "zxf_share.jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        String path = Environment.getExternalStorageDirectory() + "/CBY/" + fileName;
        return path;
    }

    /**
     * 保存图片到本地的方法
     *
     * @param context 上下文
     * @param bmp     图片
     * @param id      res下的图片ID
     * @return 图片存放地址
     */
    public static String saveImageToGallery(Context context, Bitmap bmp, int id) {

        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        String fileName;
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        if (bmp == null) {
            fileName = "zhima_logo.jpg";
        } else {
            fileName = System.currentTimeMillis() + ".jpg";
        }

        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //判断bmp是否为空  是空的话 就执行本地图片转bitmap
            if (bmp == null) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } else {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            }

            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        String path = Environment.getExternalStorageDirectory() + "/Boohee/" + fileName;
        Log.d("czb", "图片存放地址============" + path);
        return path;

    }

    /**
     * 保存图片到本地的方法
     *
     * @param context 上下文
     * @param bmp     图片
     * @param id      res下的图片ID
     * @return 图片存放地址
     */
    public static String saveImageToGallery2(Context context, Bitmap bmp, int id) {

        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        String fileName;
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        if (bmp == null) {
            fileName = "zhima_logo.jpg";
        } else {
            fileName = System.currentTimeMillis() + ".jpg";
        }

        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //判断bmp是否为空  是空的话 就执行本地图片转bitmap
            if (bmp == null) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } else {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            }

            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        String path = Environment.getExternalStorageDirectory() + "/Boohee/" + fileName;
        Log.d("czb", "图片存放地址============" + path);
        return path;

    }


    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

//    /**
//     * 显示提示加载数据的悬浮窗口
//     * 需要在当前类才能销毁
//     * @param parent  父View
//     * @param context 当前上下文
//     * @param popupWindow 空的悬浮窗口
//     * @return 初始化的悬浮窗
//     */
//	public static PopupWindow openPop(View parent,Context context,PopupWindow popupWindow){
//		View popView = LayoutInflater.from(context).inflate(R.layout.popup_prompt, null);//提示加载的布局
////		View rootView = findViewById(R.id.root_main); // 當前頁面的根佈局
//		popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//		// 设置弹出动画
////		popupWindow.setAnimationStyle(R.style.AnimationFadeBottom);
//		popupWindow.setBackgroundDrawable(new BitmapDrawable());
//		popupWindow.setFocusable(false);// 点击空白处时，隐藏掉pop窗口 true为隐藏 false为不隐藏
//		// 顯示在根佈局的底部
//		popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
//
//		return popupWindow;
//	}

    /**
     * 获取缓存大小
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除缓存
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    //清除缓存调用的子方法
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 获取文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 立木征信签名
     * @param context 当前上下文
     * @param str
     * @return
     */
//	public static String getSha1(Context context, String str) {
//		if (null == str || 0 == str.length()) {
//			return null;
//		}
//
//		//需要去服务端获取加签后的结果,
//		SignUtils.saveSecret(context, str.substring(str.length() - 32, str.length()));
//
//		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//				'a', 'b', 'c', 'd', 'e', 'f'};
//		try {
//			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
//			mdTemp.update(str.getBytes("UTF-8"));
//
//			byte[] md = mdTemp.digest();
//			int j = md.length;
//			char[] buf = new char[j * 2];
//			int k = 0;
//			for (int i = 0; i < j; i++) {
//				byte byte0 = md[i];
//				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
//				buf[k++] = hexDigits[byte0 & 0xf];
//			}
//			return new String(buf);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

    /**
     * dpתpx
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    /**
     * �����Ļ���
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 手机号用****号隐藏中间数字
     *
     * @param phone
     * @return
     */
    public static String settingPhone(String phone) {
        String phone_s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phone_s;
    }

//    /**
//     * 中文部分编码处理
//     */
//    public static String encodeUrl(String url) {
//        try {
//            Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(url);
//            while (matcher.find()) {
//                String tmp = matcher.group();
//                url = url.replaceAll(tmp, java.net.URLEncoder.encode(tmp, CHAR_SET));
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return url;
//    }
}
