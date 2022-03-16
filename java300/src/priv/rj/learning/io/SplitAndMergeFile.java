package priv.rj.learning.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SplitAndMergeFile {
    private String filePath;
    private long blockSize;
    private int size;
    private String fileName;
    private long length;
    private String destBlockPath;

    private List<String> blockPath;

    public SplitAndMergeFile() {
        blockPath = new ArrayList<String>();
    }

    public SplitAndMergeFile(String filePath, String destBlockPath) {
        this(filePath, destBlockPath, 1024);
    }

    public SplitAndMergeFile(String filePath, String destBlockPath, long blockSize) {
        this();
        this.filePath = filePath;
        this.blockSize = blockSize;
        this.destBlockPath = destBlockPath;
        init();
    }

    public void init() {
        File src = null;
        if (null == filePath || !(((src = new File(filePath)).exists()))) {
            return;
        }
        if (src.isDirectory()) {
            return;
        }
        this.fileName = src.getName();
        this.length = src.length();
        if (this.blockSize > length) {
            this.blockSize = length;
        }

        size = (int) Math.ceil(length * 1.0 / this.blockSize);
        initPathName();
    }

    public void initPathName() {
        for (int i = 0; i < size; i++) {
            this.blockPath.add(this.destBlockPath + "/" + this.fileName + ".part" + i);
        }
    }

    /**
     * 文件分割
     * 1. 第几块
     * 2. 起始位置
     * 3. 实际大小
     *
     * @param destPath
     */
    public void split(String destPath) {
        long beginPos = 0;
        long acturalBlockSize = blockSize;
        //计算所有块的大小、位置、索引
        for (int i = 0; i < size; i++) {
            if (size - 1 == i) {
                acturalBlockSize = this.length - beginPos;
            }
            splitDetail(i, beginPos, acturalBlockSize);
            beginPos += acturalBlockSize;
        }
    }

    private void splitDetail(int index, long beginPos, long acturalBlockSize) {
        //1. 创建源
        File src = new File(this.filePath);
        File dest = new File(this.blockPath.get(index));
        //选择流
        RandomAccessFile randomAccessFile = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            randomAccessFile = new RandomAccessFile(src, "r");
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));
            //读取文件
            randomAccessFile.seek(beginPos);
            //缓冲区
            byte[] flush = new byte[1024];
            //长度
            int len = 0;
            while (-1 != (len = randomAccessFile.read(flush))) {
                if (acturalBlockSize - len > 0) {
                    bufferedOutputStream.write(flush, 0, len);
                    acturalBlockSize -= len;
                } else {
                    bufferedOutputStream.write(flush, 0, (int) acturalBlockSize);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(bufferedOutputStream, randomAccessFile);
        }
    }

    public void mergeFile(String destPath) {
        File dest = new File(destPath);
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        for (int i = 0; i < this.blockPath.size(); i++) {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest, true));
                bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(this.blockPath.get(i))));
                //缓冲区
                byte[] flush = new byte[1024];
                //长度
                int len = 0;
                while (-1 != (len = bufferedInputStream.read(flush))) {
                    bufferedOutputStream.write(flush, 0, len);

                }
                bufferedOutputStream.flush();
                CloseUtils.close(bufferedInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtils.close(bufferedOutputStream);
            }
        }
    }

    public void mergeFile_SequenceInputStream(String destPath) {
        File dest = new File(destPath);
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        SequenceInputStream sequenceInputStream = null;
        Vector<InputStream> vector = new Vector<InputStream>();

        try {
            for (int i = 0; i < this.blockPath.size(); i++) {
                vector.add(new BufferedInputStream(new FileInputStream(new File(this.blockPath.get(i)))));
            }
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest, true));
            sequenceInputStream = new SequenceInputStream(vector.elements());
            //缓冲区
            byte[] flush = new byte[1024];
            //长度
            int len = 0;
            while (-1 != (len = sequenceInputStream.read(flush))) {
                bufferedOutputStream.write(flush, 0, len);

            }
            bufferedOutputStream.flush();
            CloseUtils.close(sequenceInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(bufferedOutputStream);
        }
    }

    public static void main(String[] args) {
        SplitAndMergeFile splitFile = new SplitAndMergeFile("java300/src/priv/rj/learning/io/test/test.txt", "java300/src/priv/rj/learning/io/test/split", 20);
        System.out.println(splitFile.size);
        splitFile.mergeFile("java300/src/priv/rj/learning/io/test/split/mergetest.txt");
        splitFile.mergeFile_SequenceInputStream("java300/src/priv/rj/learning/io/test/split/mergetest_usesequenceinputstream.txt");
    }
}
