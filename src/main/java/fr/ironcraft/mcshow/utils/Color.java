package fr.ironcraft.mcshow.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/**
 * A color
 *
 * @author Wytrem
 * @version 0.1.0
 * @since 0.0.1
 */
public class Color {
    /**
     * The color mask (to signed and unsigned byte)
     */
    private static final int MASK = 0xff;

    /**
     * A fully transparent color with an ARGB value of #00000000.
     */
    public static final Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);

    /**
     * The color alice blue with an RGB value of #F0F8FF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F0F8FF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color ALICEBLUE = new Color(0.9411765f, 0.972549f, 1.0f);

    /**
     * The color antique white with an RGB value of #FAEBD7
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FAEBD7;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color ANTIQUEWHITE = new Color(0.98039216f, 0.92156863f, 0.84313726f);

    /**
     * The color aqua with an RGB value of #00FFFF <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00FFFF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color AQUA = new Color(0.0f, 1.0f, 1.0f);

    /**
     * The color aquamarine with an RGB value of #7FFFD4
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#7FFFD4;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color AQUAMARINE = new Color(0.49803922f, 1.0f, 0.83137256f);

    /**
     * The color azure with an RGB value of #F0FFFF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F0FFFF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color AZURE = new Color(0.9411765f, 1.0f, 1.0f);

    /**
     * The color beige with an RGB value of #F5F5DC
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F5F5DC;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BEIGE = new Color(0.9607843f, 0.9607843f, 0.8627451f);

    /**
     * The color bisque with an RGB value of #FFE4C4
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFE4C4;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BISQUE = new Color(1.0f, 0.89411765f, 0.76862746f);

    /**
     * The color black with an RGB value of #000000
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#000000;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);

    /**
     * The color blanched almond with an RGB value of #FFEBCD
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFEBCD;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BLANCHEDALMOND = new Color(1.0f, 0.92156863f, 0.8039216f);

    /**
     * The color blue with an RGB value of #0000FF <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#0000FF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);

    /**
     * The color blue violet with an RGB value of #8A2BE2
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#8A2BE2;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BLUEVIOLET = new Color(0.5411765f, 0.16862746f, 0.8862745f);

    /**
     * The color brown with an RGB value of #A52A2A
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#A52A2A;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BROWN = new Color(0.64705884f, 0.16470589f, 0.16470589f);

    /**
     * The color burly wood with an RGB value of #DEB887
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#DEB887;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color BURLYWOOD = new Color(0.87058824f, 0.72156864f, 0.5294118f);

    /**
     * The color cadet blue with an RGB value of #5F9EA0
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#5F9EA0;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CADETBLUE = new Color(0.37254903f, 0.61960787f, 0.627451f);

    /**
     * The color chartreuse with an RGB value of #7FFF00
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#7FFF00;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CHARTREUSE = new Color(0.49803922f, 1.0f, 0.0f);

    /**
     * The color chocolate with an RGB value of #D2691E
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#D2691E;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CHOCOLATE = new Color(0.8235294f, 0.4117647f, 0.11764706f);

    /**
     * The color coral with an RGB value of #FF7F50
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF7F50;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CORAL = new Color(1.0f, 0.49803922f, 0.3137255f);

    /**
     * The color cornflower blue with an RGB value of #6495ED
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#6495ED;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CORNFLOWERBLUE = new Color(0.39215687f, 0.58431375f, 0.92941177f);

    /**
     * The color cornsilk with an RGB value of #FFF8DC
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFF8DC;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CORNSILK = new Color(1.0f, 0.972549f, 0.8627451f);

    /**
     * The color crimson with an RGB value of #DC143C
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#DC143C;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CRIMSON = new Color(0.8627451f, 0.078431375f, 0.23529412f);

    /**
     * The color cyan with an RGB value of #00FFFF <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00FFFF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f);

    /**
     * The color dark blue with an RGB value of #00008B
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00008B;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKBLUE = new Color(0.0f, 0.0f, 0.54509807f);

    /**
     * The color dark cyan with an RGB value of #008B8B
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#008B8B;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKCYAN = new Color(0.0f, 0.54509807f, 0.54509807f);

    /**
     * The color dark goldenrod with an RGB value of #B8860B
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#B8860B;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKGOLDENROD = new Color(0.72156864f, 0.5254902f, 0.043137256f);

    /**
     * The color dark gray with an RGB value of #A9A9A9
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#A9A9A9;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKGRAY = new Color(0.6627451f, 0.6627451f, 0.6627451f);

    /**
     * The color dark green with an RGB value of #006400
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#006400;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKGREEN = new Color(0.0f, 0.39215687f, 0.0f);

    /**
     * The color dark grey with an RGB value of #A9A9A9
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#A9A9A9;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKGREY = DARKGRAY;

    /**
     * The color dark khaki with an RGB value of #BDB76B
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#BDB76B;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKKHAKI = new Color(0.7411765f, 0.7176471f, 0.41960785f);

    /**
     * The color dark magenta with an RGB value of #8B008B
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#8B008B;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKMAGENTA = new Color(0.54509807f, 0.0f, 0.54509807f);

    /**
     * The color dark olive green with an RGB value of #556B2F
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#556B2F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKOLIVEGREEN = new Color(0.33333334f, 0.41960785f, 0.18431373f);

    /**
     * The color dark orange with an RGB value of #FF8C00
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF8C00;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKORANGE = new Color(1.0f, 0.54901963f, 0.0f);

    /**
     * The color dark orchid with an RGB value of #9932CC
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#9932CC;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKORCHID = new Color(0.6f, 0.19607843f, 0.8f);

    /**
     * The color dark red with an RGB value of #8B0000
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#8B0000;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKRED = new Color(0.54509807f, 0.0f, 0.0f);

    /**
     * The color dark salmon with an RGB value of #E9967A
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#E9967A;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKSALMON = new Color(0.9137255f, 0.5882353f, 0.47843137f);

    /**
     * The color dark sea green with an RGB value of #8FBC8F
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#8FBC8F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKSEAGREEN = new Color(0.56078434f, 0.7372549f, 0.56078434f);

    /**
     * The color dark slate blue with an RGB value of #483D8B
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#483D8B;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKSLATEBLUE = new Color(0.28235295f, 0.23921569f, 0.54509807f);

    /**
     * The color dark slate gray with an RGB value of #2F4F4F
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#2F4F4F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKSLATEGRAY = new Color(0.18431373f, 0.30980393f, 0.30980393f);

    /**
     * The color dark slate grey with an RGB value of #2F4F4F
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#2F4F4F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKSLATEGREY = DARKSLATEGRAY;

    /**
     * The color dark turquoise with an RGB value of #00CED1
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00CED1;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKTURQUOISE = new Color(0.0f, 0.80784315f, 0.81960785f);

    /**
     * The color dark violet with an RGB value of #9400D3
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#9400D3;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DARKVIOLET = new Color(0.5803922f, 0.0f, 0.827451f);

    /**
     * The color deep pink with an RGB value of #FF1493
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF1493;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DEEPPINK = new Color(1.0f, 0.078431375f, 0.5764706f);

    /**
     * The color deep sky blue with an RGB value of #00BFFF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00BFFF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DEEPSKYBLUE = new Color(0.0f, 0.7490196f, 1.0f);

    /**
     * The color dim gray with an RGB value of #696969
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#696969;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DIMGRAY = new Color(0.4117647f, 0.4117647f, 0.4117647f);

    /**
     * The color dim grey with an RGB value of #696969
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#696969;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DIMGREY = DIMGRAY;

    /**
     * The color dodger blue with an RGB value of #1E90FF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#1E90FF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color DODGERBLUE = new Color(0.11764706f, 0.5647059f, 1.0f);

    /**
     * The color firebrick with an RGB value of #B22222
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#B22222;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color FIREBRICK = new Color(0.69803923f, 0.13333334f, 0.13333334f);

    /**
     * The color floral white with an RGB value of #FFFAF0
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFFAF0;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color FLORALWHITE = new Color(1.0f, 0.98039216f, 0.9411765f);

    /**
     * The color forest green with an RGB value of #228B22
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#228B22;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color FORESTGREEN = new Color(0.13333334f, 0.54509807f, 0.13333334f);

    /**
     * The color fuchsia with an RGB value of #FF00FF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF00FF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color FUCHSIA = new Color(1.0f, 0.0f, 1.0f);

    /**
     * The color gainsboro with an RGB value of #DCDCDC
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#DCDCDC;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GAINSBORO = new Color(0.8627451f, 0.8627451f, 0.8627451f);

    /**
     * The color ghost white with an RGB value of #F8F8FF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F8F8FF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GHOSTWHITE = new Color(0.972549f, 0.972549f, 1.0f);

    /**
     * The color gold with an RGB value of #FFD700 <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFD700;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GOLD = new Color(1.0f, 0.84313726f, 0.0f);

    /**
     * The color goldenrod with an RGB value of #DAA520
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#DAA520;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GOLDENROD = new Color(0.85490197f, 0.64705884f, 0.1254902f);

    /**
     * The color gray with an RGB value of #808080 <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#808080;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GRAY = new Color(0.5019608f, 0.5019608f, 0.5019608f);

    /**
     * The color green with an RGB value of #008000
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#008000;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GREEN = new Color(0.0f, 0.5019608f, 0.0f);

    /**
     * The color green yellow with an RGB value of #ADFF2F
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#ADFF2F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GREENYELLOW = new Color(0.6784314f, 1.0f, 0.18431373f);

    /**
     * The color grey with an RGB value of #808080 <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#808080;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color GREY = GRAY;

    /**
     * The color honeydew with an RGB value of #F0FFF0
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F0FFF0;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color HONEYDEW = new Color(0.9411765f, 1.0f, 0.9411765f);

    /**
     * The color hot pink with an RGB value of #FF69B4
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF69B4;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color HOTPINK = new Color(1.0f, 0.4117647f, 0.7058824f);

    /**
     * The color indian red with an RGB value of #CD5C5C
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#CD5C5C;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color INDIANRED = new Color(0.8039216f, 0.36078432f, 0.36078432f);

    /**
     * The color indigo with an RGB value of #4B0082
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#4B0082;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color INDIGO = new Color(0.29411766f, 0.0f, 0.50980395f);

    /**
     * The color ivory with an RGB value of #FFFFF0
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFFFF0;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color IVORY = new Color(1.0f, 1.0f, 0.9411765f);

    /**
     * The color khaki with an RGB value of #F0E68C
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F0E68C;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color KHAKI = new Color(0.9411765f, 0.9019608f, 0.54901963f);

    /**
     * The color lavender with an RGB value of #E6E6FA
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#E6E6FA;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LAVENDER = new Color(0.9019608f, 0.9019608f, 0.98039216f);

    /**
     * The color lavender blush with an RGB value of #FFF0F5
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFF0F5;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LAVENDERBLUSH = new Color(1.0f, 0.9411765f, 0.9607843f);

    /**
     * The color lawn green with an RGB value of #7CFC00
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#7CFC00;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LAWNGREEN = new Color(0.4862745f, 0.9882353f, 0.0f);

    /**
     * The color lemon chiffon with an RGB value of #FFFACD
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFFACD;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LEMONCHIFFON = new Color(1.0f, 0.98039216f, 0.8039216f);

    /**
     * The color light blue with an RGB value of #ADD8E6
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#ADD8E6;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTBLUE = new Color(0.6784314f, 0.84705883f, 0.9019608f);

    /**
     * The color light coral with an RGB value of #F08080
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F08080;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTCORAL = new Color(0.9411765f, 0.5019608f, 0.5019608f);

    /**
     * The color light cyan with an RGB value of #E0FFFF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#E0FFFF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTCYAN = new Color(0.8784314f, 1.0f, 1.0f);

    /**
     * The color light goldenrod yellow with an RGB value of #FAFAD2
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FAFAD2;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTGOLDENRODYELLOW = new Color(0.98039216f, 0.98039216f, 0.8235294f);

    /**
     * The color light gray with an RGB value of #D3D3D3
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#D3D3D3;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTGRAY = new Color(0.827451f, 0.827451f, 0.827451f);

    /**
     * The color light green with an RGB value of #90EE90
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#90EE90;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTGREEN = new Color(0.5647059f, 0.93333334f, 0.5647059f);

    /**
     * The color light grey with an RGB value of #D3D3D3
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#D3D3D3;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTGREY = LIGHTGRAY;

    /**
     * The color light pink with an RGB value of #FFB6C1
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFB6C1;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTPINK = new Color(1.0f, 0.7137255f, 0.75686276f);

    /**
     * The color light salmon with an RGB value of #FFA07A
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFA07A;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTSALMON = new Color(1.0f, 0.627451f, 0.47843137f);

    /**
     * The color light sea green with an RGB value of #20B2AA
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#20B2AA;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTSEAGREEN = new Color(0.1254902f, 0.69803923f, 0.6666667f);

    /**
     * The color light sky blue with an RGB value of #87CEFA
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#87CEFA;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTSKYBLUE = new Color(0.5294118f, 0.80784315f, 0.98039216f);

    /**
     * The color light slate gray with an RGB value of #778899
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#778899;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTSLATEGRAY = new Color(0.46666667f, 0.53333336f, 0.6f);

    /**
     * The color light slate grey with an RGB value of #778899
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#778899;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTSLATEGREY = LIGHTSLATEGRAY;

    /**
     * The color light steel blue with an RGB value of #B0C4DE
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#B0C4DE;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTSTEELBLUE = new Color(0.6901961f, 0.76862746f, 0.87058824f);

    /**
     * The color light yellow with an RGB value of #FFFFE0
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFFFE0;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIGHTYELLOW = new Color(1.0f, 1.0f, 0.8784314f);

    /**
     * The color lime with an RGB value of #00FF00 <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00FF00;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIME = new Color(0.0f, 1.0f, 0.0f);

    /**
     * The color lime green with an RGB value of #32CD32
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#32CD32;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LIMEGREEN = new Color(0.19607843f, 0.8039216f, 0.19607843f);

    /**
     * The color linen with an RGB value of #FAF0E6
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FAF0E6;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color LINEN = new Color(0.98039216f, 0.9411765f, 0.9019608f);

    /**
     * The color magenta with an RGB value of #FF00FF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF00FF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);

    /**
     * The color maroon with an RGB value of #800000
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#800000;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MAROON = new Color(0.5019608f, 0.0f, 0.0f);

    /**
     * The color medium aquamarine with an RGB value of #66CDAA
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#66CDAA;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMAQUAMARINE = new Color(0.4f, 0.8039216f, 0.6666667f);

    /**
     * The color medium blue with an RGB value of #0000CD
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#0000CD;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMBLUE = new Color(0.0f, 0.0f, 0.8039216f);

    /**
     * The color medium orchid with an RGB value of #BA55D3
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#BA55D3;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMORCHID = new Color(0.7294118f, 0.33333334f, 0.827451f);

    /**
     * The color medium purple with an RGB value of #9370DB
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#9370DB;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMPURPLE = new Color(0.5764706f, 0.4392157f, 0.85882354f);

    /**
     * The color medium sea green with an RGB value of #3CB371
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#3CB371;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMSEAGREEN = new Color(0.23529412f, 0.7019608f, 0.44313726f);

    /**
     * The color medium slate blue with an RGB value of #7B68EE
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#7B68EE;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMSLATEBLUE = new Color(0.48235294f, 0.40784314f, 0.93333334f);

    /**
     * The color medium spring green with an RGB value of #00FA9A
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00FA9A;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMSPRINGGREEN = new Color(0.0f, 0.98039216f, 0.6039216f);

    /**
     * The color medium turquoise with an RGB value of #48D1CC
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#48D1CC;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMTURQUOISE = new Color(0.28235295f, 0.81960785f, 0.8f);

    /**
     * The color medium violet red with an RGB value of #C71585
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#C71585;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MEDIUMVIOLETRED = new Color(0.78039217f, 0.08235294f, 0.52156866f);

    /**
     * The color midnight blue with an RGB value of #191970
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#191970;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MIDNIGHTBLUE = new Color(0.09803922f, 0.09803922f, 0.4392157f);

    /**
     * The color mint cream with an RGB value of #F5FFFA
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F5FFFA;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MINTCREAM = new Color(0.9607843f, 1.0f, 0.98039216f);

    /**
     * The color misty rose with an RGB value of #FFE4E1
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFE4E1;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MISTYROSE = new Color(1.0f, 0.89411765f, 0.88235295f);

    /**
     * The color moccasin with an RGB value of #FFE4B5
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFE4B5;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color MOCCASIN = new Color(1.0f, 0.89411765f, 0.70980394f);

    /**
     * The color navajo white with an RGB value of #FFDEAD
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFDEAD;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color NAVAJOWHITE = new Color(1.0f, 0.87058824f, 0.6784314f);

    /**
     * The color navy with an RGB value of #000080 <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#000080;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color NAVY = new Color(0.0f, 0.0f, 0.5019608f);

    /**
     * The color old lace with an RGB value of #FDF5E6
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FDF5E6;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color OLDLACE = new Color(0.99215686f, 0.9607843f, 0.9019608f);

    /**
     * The color olive with an RGB value of #808000
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#808000;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color OLIVE = new Color(0.5019608f, 0.5019608f, 0.0f);

    /**
     * The color olive drab with an RGB value of #6B8E23
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#6B8E23;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color OLIVEDRAB = new Color(0.41960785f, 0.5568628f, 0.13725491f);

    /**
     * The color orange with an RGB value of #FFA500
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFA500;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color ORANGE = new Color(1.0f, 0.64705884f, 0.0f);

    /**
     * The color orange red with an RGB value of #FF4500
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF4500;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color ORANGERED = new Color(1.0f, 0.27058825f, 0.0f);

    /**
     * The color orchid with an RGB value of #DA70D6
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#DA70D6;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color ORCHID = new Color(0.85490197f, 0.4392157f, 0.8392157f);

    /**
     * The color pale goldenrod with an RGB value of #EEE8AA
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#EEE8AA;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PALEGOLDENROD = new Color(0.93333334f, 0.9098039f, 0.6666667f);

    /**
     * The color pale green with an RGB value of #98FB98
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#98FB98;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PALEGREEN = new Color(0.59607846f, 0.9843137f, 0.59607846f);

    /**
     * The color pale turquoise with an RGB value of #AFEEEE
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#AFEEEE;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PALETURQUOISE = new Color(0.6862745f, 0.93333334f, 0.93333334f);

    /**
     * The color pale violet red with an RGB value of #DB7093
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#DB7093;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PALEVIOLETRED = new Color(0.85882354f, 0.4392157f, 0.5764706f);

    /**
     * The color papaya whip with an RGB value of #FFEFD5
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFEFD5;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PAPAYAWHIP = new Color(1.0f, 0.9372549f, 0.8352941f);

    /**
     * The color peach puff with an RGB value of #FFDAB9
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFDAB9;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PEACHPUFF = new Color(1.0f, 0.85490197f, 0.7254902f);

    /**
     * The color peru with an RGB value of #CD853F <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#CD853F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PERU = new Color(0.8039216f, 0.52156866f, 0.24705882f);

    /**
     * The color pink with an RGB value of #FFC0CB <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFC0CB;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PINK = new Color(1.0f, 0.7529412f, 0.79607844f);

    /**
     * The color plum with an RGB value of #DDA0DD <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#DDA0DD;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PLUM = new Color(0.8666667f, 0.627451f, 0.8666667f);

    /**
     * The color powder blue with an RGB value of #B0E0E6
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#B0E0E6;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color POWDERBLUE = new Color(0.6901961f, 0.8784314f, 0.9019608f);

    /**
     * The color purple with an RGB value of #800080
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#800080;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color PURPLE = new Color(0.5019608f, 0.0f, 0.5019608f);

    /**
     * The color red with an RGB value of #FF0000 <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF0000;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f);

    /**
     * The color rosy brown with an RGB value of #BC8F8F
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#BC8F8F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color ROSYBROWN = new Color(0.7372549f, 0.56078434f, 0.56078434f);

    /**
     * The color royal blue with an RGB value of #4169E1
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#4169E1;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color ROYALBLUE = new Color(0.25490198f, 0.4117647f, 0.88235295f);

    /**
     * The color saddle brown with an RGB value of #8B4513
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#8B4513;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SADDLEBROWN = new Color(0.54509807f, 0.27058825f, 0.07450981f);

    /**
     * The color salmon with an RGB value of #FA8072
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FA8072;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SALMON = new Color(0.98039216f, 0.5019608f, 0.44705883f);

    /**
     * The color sandy brown with an RGB value of #F4A460
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F4A460;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SANDYBROWN = new Color(0.95686275f, 0.6431373f, 0.3764706f);

    /**
     * The color sea green with an RGB value of #2E8B57
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#2E8B57;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SEAGREEN = new Color(0.18039216f, 0.54509807f, 0.34117648f);

    /**
     * The color sea shell with an RGB value of #FFF5EE
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFF5EE;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SEASHELL = new Color(1.0f, 0.9607843f, 0.93333334f);

    /**
     * The color sienna with an RGB value of #A0522D
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#A0522D;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SIENNA = new Color(0.627451f, 0.32156864f, 0.1764706f);

    /**
     * The color silver with an RGB value of #C0C0C0
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#C0C0C0;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SILVER = new Color(0.7529412f, 0.7529412f, 0.7529412f);

    /**
     * The color sky blue with an RGB value of #87CEEB
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#87CEEB;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SKYBLUE = new Color(0.5294118f, 0.80784315f, 0.92156863f);

    /**
     * The color slate blue with an RGB value of #6A5ACD
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#6A5ACD;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SLATEBLUE = new Color(0.41568628f, 0.3529412f, 0.8039216f);

    /**
     * The color slate gray with an RGB value of #708090
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#708090;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SLATEGRAY = new Color(0.4392157f, 0.5019608f, 0.5647059f);

    /**
     * The color slate grey with an RGB value of #708090
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#708090;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SLATEGREY = SLATEGRAY;

    /**
     * The color snow with an RGB value of #FFFAFA <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFFAFA;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SNOW = new Color(1.0f, 0.98039216f, 0.98039216f);

    /**
     * The color spring green with an RGB value of #00FF7F
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#00FF7F;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color SPRINGGREEN = new Color(0.0f, 1.0f, 0.49803922f);

    /**
     * The color steel blue with an RGB value of #4682B4
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#4682B4;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color STEELBLUE = new Color(0.27450982f, 0.50980395f, 0.7058824f);

    /**
     * The color tan with an RGB value of #D2B48C <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#D2B48C;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color TAN = new Color(0.8235294f, 0.7058824f, 0.54901963f);

    /**
     * The color teal with an RGB value of #008080 <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#008080;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color TEAL = new Color(0.0f, 0.5019608f, 0.5019608f);

    /**
     * The color thistle with an RGB value of #D8BFD8
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#D8BFD8;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color THISTLE = new Color(0.84705883f, 0.7490196f, 0.84705883f);

    /**
     * The color tomato with an RGB value of #FF6347
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FF6347;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color TOMATO = new Color(1.0f, 0.3882353f, 0.2784314f);

    /**
     * The color turquoise with an RGB value of #40E0D0
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#40E0D0;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color TURQUOISE = new Color(0.2509804f, 0.8784314f, 0.8156863f);

    /**
     * The color violet with an RGB value of #EE82EE
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#EE82EE;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color VIOLET = new Color(0.93333334f, 0.50980395f, 0.93333334f);

    /**
     * The color wheat with an RGB value of #F5DEB3
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F5DEB3;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color WHEAT = new Color(0.9607843f, 0.87058824f, 0.7019608f);

    /**
     * The color white with an RGB value of #FFFFFF
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFFFFF;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);

    /**
     * The color white smoke with an RGB value of #F5F5F5
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#F5F5F5;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color WHITESMOKE = new Color(0.9607843f, 0.9607843f, 0.9607843f);

    /**
     * The color yellow with an RGB value of #FFFF00
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#FFFF00;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f);

    /**
     * The color yellow green with an RGB value of #9ACD32
     * <div style= "border:1px solid
     * black;width:40px;height:20px;background-color:#9ACD32;float:right;margin:
     * 0 10px 0 0" ></div><br/>
     * <br/>
     */
    public static final Color YELLOWGREEN = new Color(0.6039216f, 0.8039216f, 0.19607843f);

    /**
     * Red component of this color (0-255).
     */
    private final byte red;

    /**
     * Green component of this color (0-255).
     */
    private final byte green;

    /**
     * Blue component of this color (0-255).
     */
    private final byte blue;

    /**
     * Alpha (transparency) component of this color (0-255).
     */
    private final byte alpha;

    /**
     * A color (opaque)
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     */
    public Color(float red, float green, float blue) {
        this(red, green, blue, 1.0f);
    }

    public Color(int argb) {
        this((argb >> 16) & MASK, (argb >> 8) & MASK, (argb) & MASK, (argb >> 24) & MASK);
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(int r, int g, int b, int a) {
        this((byte) r, (byte) g, (byte) b, (byte) a);
    }

    /**
     * A color
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     * @param alpha The transparency of the color
     */
    public Color(float red, float green, float blue, float alpha) {
        this((byte) (red * 255.0f), (byte) (green * 255.0f), (byte) (blue * 255.0f), (byte) (alpha * 255.0f));
    }

    /**
     * A color (opaque)
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     */
    public Color(byte red, byte green, byte blue) {
        this(red, green, blue, Byte.MAX_VALUE);
    }

    /**
     * A color
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     * @param alpha The transparency of the color
     */
    public Color(byte red, byte green, byte blue, byte alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * Gets the red.
     *
     * @return the red.
     */
    public byte getRed() {
        return (byte) (red & MASK);
    }

    /**
     * Gets the green.
     *
     * @return the green.
     */
    public byte getGreen() {
        return (byte) (green & MASK);
    }

    /**
     * Gets the blue.
     *
     * @return the blue.
     */
    public byte getBlue() {
        return (byte) (blue & MASK);
    }

    /**
     * Gets the alpha.
     *
     * @return the alpha.
     */
    public byte getAlpha() {
        return (byte) (alpha & MASK);
    }

    /**
     * Gets the red as a float
     *
     * @return The red
     */
    public float getRedFloat() {
        return (red & MASK) / 255.0f;
    }

    /**
     * Gets the green as a float
     *
     * @return The green
     */
    public float getGreenFloat() {
        return (green & MASK) / 255.0f;
    }

    /**
     * Gets the blue as a float
     *
     * @return The blue
     */
    public float getBlueFloat() {
        return (blue & MASK) / 255.0f;
    }

    /**
     * Gets the alpha as a float
     *
     * @return The alpha
     */
    public float getAlphaFloat() {
        return (alpha & MASK) / 255.0f;
    }

    @Override
    public String toString() {
        return "Color [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + ", getRed()=" + getRed() + ", getGreen()=" + getGreen() + ", getBlue()=" + getBlue() + ", getAlpha()=" + getAlpha() + ", getRedFloat()=" + getRedFloat() + ", getGreenFloat()=" + getGreenFloat() + ", getBlueFloat()=" + getBlueFloat() + ", getAlphaFloat()=" + getAlphaFloat() + "]";
    }

    private static final Color[] MINECRAFT_CODES = {new Color(0xff000000), new Color(0xff0000aa), new Color(0xff00aa00), new Color(0xff00aaaa), new Color(0xffaa0000), new Color(0xffaa00aa), new Color(0xffffaa00), new Color(0xffaaaaaa), new Color(0xff555555), new Color(0xff5555ff), new Color(0xff55ff55), new Color(0xff55ffff), new Color(0xffff5555), new Color(0xffff55ff), new Color(0xffffff55), new Color(0xffffffff), new Color(0xff000000), new Color(0xff00002a), new Color(0xff002a00), new Color(0xff002a2a), new Color(0xff2a0000), new Color(0xff2a002a), new Color(0xff2a2a00), new Color(0xff2a2a2a), new Color(0xff151515), new Color(0xff15153f), new Color(0xff153f15), new Color(0xff153f3f), new Color(0xff3f1515), new Color(0xff3f153f), new Color(0xff3f3f15), new Color(0xff3f3f3f), new Color(0xff000000), new Color(0xff0000aa), new Color(0xff00aa00), new Color(0xff00aaaa), new Color(0xffaa0000), new Color(0xffaa00aa), new Color(0xffffaa00), new Color(0xffaaaaaa), new Color(0xff555555), new Color(0xff5555ff), new Color(0xff55ff55), new Color(0xff55ffff), new Color(0xffff5555), new Color(0xffff55ff), new Color(0xffffff55), new Color(0xffffffff), new Color(0xff000000), new Color(0xff00002a), new Color(0xff002a00), new Color(0xff002a2a), new Color(0xff2a0000), new Color(0xff2a002a), new Color(0xff2a2a00), new Color(0xff2a2a2a), new Color(0xff151515), new Color(0xff15153f), new Color(0xff153f15), new Color(0xff153f3f), new Color(0xff3f1515), new Color(0xff3f153f), new Color(0xff3f3f15), new Color(0xff3f3f3f)};

    public static Color valueOf(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        else if (str.length() == 1) {
            int index = "0123456789abcdefklmnor".indexOf(str.toLowerCase());
            return MINECRAFT_CODES[index];
        }
        else {
            try {
                int color = (int) Long.parseLong(str, 16);
                return new Color(color);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class JsonAdapter
                    implements JsonSerializer<Color>, JsonDeserializer<Color> {

        @Override
        public Color deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {

                JsonObject jsonObject = json.getAsJsonObject();

                return new Color(jsonObject.get("red")
                                           .getAsFloat(), jsonObject.get("green")
                                                                    .getAsFloat(), jsonObject.get("blue")
                                                                                             .getAsFloat(), jsonObject.get("alpha")
                                                                                                                      .getAsFloat());

            }
            else if (json.isJsonPrimitive()) {
                return valueOf(json.getAsString());
            }
            return null;
        }

        @Override
        public JsonElement serialize(Color src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("red", src.getRedFloat());
            jsonObject.addProperty("green", src.getGreenFloat());
            jsonObject.addProperty("blue", src.getBlueFloat());
            jsonObject.addProperty("alpha", src.getAlphaFloat());
            return jsonObject;
        }

    }
}
