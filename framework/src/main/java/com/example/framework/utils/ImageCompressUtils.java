/**
 * @Title: ImageCompressUtils.java @Package
 * com.zhiding.mobile.am.commoncomponents.utils @Description: TODO(用一句话描述该文件做什么)
 *
 * @author chaunce
 * @date 2015-5-25 下午4:25:23
 * @version V1.0.0
 */
package com.example.framework.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chaunce @ClassName: ImageCompressUtils @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015-5-25 下午4:25:23
 */
public class ImageCompressUtils {
  // ===========================================================
  // Constants
  // ===========================================================
  public static final int defaultReqHeight = 480;
  public static final int defaultReqWidth = 800;

  // ===========================================================
  // Fields
  // ===========================================================

  // ===========================================================
  // Constructors
  // ===========================================================

  // ===========================================================
  // Getter & Setter
  // ===========================================================

  // ===========================================================
  // Methods for/from SuperClass/Interfaces
  // ===========================================================

  // ===========================================================
  // Methods
  // ===========================================================

  /**
   * @param @param options
   * @param @param reqWidth
   * @param @param reqHeight
   * @param @return 设定文件
   * @return int 返回类型 @Title: calculateInSampleSize @Description: TODO(计算缩放比例)
   */
  public static int calculateInSampleSize(
      BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

      // Calculate ratios of height and width to requested height and width
      final int heightRatio = Math.round((float) height / (float) reqHeight);
      final int widthRatio = Math.round((float) width / (float) reqWidth);

      // Choose the smallest ratio as inSampleSize value, this will guarantee
      // a final image with both dimensions larger than or equal to the
      // requested height and width.
      inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
    }

    return inSampleSize;
  }

  /**
   * @param @param res
   * @param @param resId
   * @param @param reqWidth
   * @param @param reqHeight
   * @param @return 设定文件
   * @return Bitmap 返回类型 @Title: decodeSampledBitmapFromResource @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static Bitmap decodeSampledBitmapFromResource(
      Resources res, int resId, int reqWidth, int reqHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(res, resId, options);
  }

  /**
   * @param @param bmp
   * @param @return 设定文件
   * @return Bitmap 返回类型 @Title: compressImageToBitmap @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static Bitmap compressImageToBitmap(Bitmap bmp) {

    if (bmp != null) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      bmp.compress(Bitmap.CompressFormat.JPEG, 80, baos); // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
      int options = 100;
      while (baos.toByteArray().length / 1024 > 300) { // 循环判断如果压缩后图片是否大于300kb,大于继续压缩

        baos.reset(); // 重置baos即清空baos
        options -= 10; // 每次都减少10
        if (options < 10) {
          options = 10;
          bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
          break;
        }
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
      }

      if (bmp != null && !bmp.isRecycled()) {
        bmp.recycle();
      }
      ByteArrayInputStream isBm =
          new ByteArrayInputStream(baos.toByteArray()); // 把压缩后的数据baos存放到ByteArrayInputStream中
      Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null); // 把ByteArrayInputStream数据生成图片
      return bitmap;
    }

    return bmp;
  }

  /**
   * @param @param bmp
   * @param @return 设定文件
   * @return InputStream 返回类型 @Title: compressImageToStream @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static InputStream compressImageToStream(Bitmap bmp) {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    if (bmp != null) {
      bmp.compress(Bitmap.CompressFormat.JPEG, 80, baos); // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
      int options = 100;
      while (baos.toByteArray().length / 1024 > 300) { // 循环判断如果压缩后图片是否大于300kb,大于继续压缩

        baos.reset(); // 重置baos即清空baos
        options -= 10; // 每次都减少10
        if (options < 10) {
          options = 10;
          bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
          break;
        }
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
      }
      ByteArrayInputStream isBm =
          new ByteArrayInputStream(baos.toByteArray()); // 把压缩后的数据baos存放到ByteArrayInputStream中
      bmp.recycle();

      return isBm;
    }
    return null;
  }

  /**
   * @param @param srcPath
   * @param @param reqWidth
   * @param @param reqHeight
   * @param @return 设定文件
   * @return Bitmap 返回类型 @Title: getImageBitmapCompressed @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static Bitmap getImageBitmapCompressed(String srcPath, int reqWidth, int reqHeight) {
    BitmapFactory.Options newOpts = new BitmapFactory.Options();
    newOpts.inJustDecodeBounds = true;

    BitmapFactory.decodeFile(srcPath, newOpts);

    Log.d("getImageBitmapCompressed", "original height: " + newOpts.outHeight);
    Log.d("getImageBitmapCompressed", "original width: " + newOpts.outWidth);

    if (newOpts.outHeight == -1 || newOpts.outWidth == -1) {
      try {
        ExifInterface exifInterface = new ExifInterface(srcPath);
        int height =
            exifInterface.getAttributeInt(
                ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL); //获取图片的高度
        int width =
            exifInterface.getAttributeInt(
                ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL); //获取图片的宽度
        Log.d("getImageBitmapCompressed", "exif height: " + height);
        Log.d("getImageBitmapCompressed", "exif width: " + width);
        newOpts.outWidth = width;
        newOpts.outHeight = height;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    newOpts.inSampleSize = calculateInSampleSize(newOpts, reqWidth, reqHeight); // 设置缩放比例
    newOpts.inJustDecodeBounds = false;
    // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
    Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
    return compressImageToBitmap(bitmap); // 压缩好比例大小后再进行质量压缩
  }

  /**
   * @param @param srcPath
   * @param @return 设定文件
   * @return Bitmap 返回类型 @Title: getImageBitmapCompressed @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static Bitmap getImageBitmapCompressed(String srcPath) {
    return getImageBitmapCompressed(srcPath, defaultReqWidth, defaultReqHeight);
  }

  /**
   * @param @param srcPath
   * @param @param reqWidth
   * @param @param reqHeight
   * @param @return 设定文件
   * @return InputStream 返回类型 @Title: getImageStreamCompressed @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static InputStream getImageStreamCompressed(String srcPath, int reqWidth, int reqHeight) {
    BitmapFactory.Options newOpts = new BitmapFactory.Options();
    newOpts.inJustDecodeBounds = true;

    BitmapFactory.decodeFile(srcPath, newOpts);

    if (newOpts.outHeight == -1 || newOpts.outWidth == -1) {
      try {
        ExifInterface exifInterface = new ExifInterface(srcPath);
        int height =
            exifInterface.getAttributeInt(
                ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL); //获取图片的高度
        int width =
            exifInterface.getAttributeInt(
                ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL); //获取图片的宽度
        Log.d("getImageBitmapCompressed", "exif height: " + height);
        Log.d("getImageBitmapCompressed", "exif width: " + width);
        newOpts.outWidth = width;
        newOpts.outHeight = height;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    newOpts.inSampleSize = calculateInSampleSize(newOpts, reqWidth, reqHeight); // 设置缩放比例
    newOpts.inJustDecodeBounds = false;
    // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
    Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
    return compressImageToStream(bitmap); // 压缩好比例大小后再进行质量压缩
  }

  /**
   * @param @param srcPath
   * @param @return 设定文件
   * @return InputStream 返回类型 @Title: getImageStreamCompressed @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static InputStream getImageStreamCompressed(String srcPath) {
    return getImageStreamCompressed(srcPath, defaultReqWidth, defaultReqHeight);
  }

  public static Bitmap getImageBitmap(InputStream stream) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    //BitmapFactory.decodeStream(stream, null, options);
    //int imageHeight = options.outHeight;
    //int imageWidth = options.outWidth;
    //// recreate the stream
    //// make some calculation to define inSampleSize
    options.inSampleSize = calculateInSampleSize(options, defaultReqWidth, defaultReqHeight);
    options.inJustDecodeBounds = false;
    Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
    if (bitmap != null) {
      return compressImageToBitmap(bitmap);
    }
    return bitmap;
  }

  // ===========================================================
  // Inner and Anonymous Classes
  // ===========================================================
}
