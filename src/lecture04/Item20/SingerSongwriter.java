package lecture04.Item20;

import java.applet.AudioClip;

public interface SingerSongwriter extends Singer, SongWriter {
    AudioClip strum();
    void actSensitive();
}