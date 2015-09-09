package models;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.badlogic.audio.io.MP3Decoder;

@Entity
public class AudioData
{
	
	@Id
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq", sequenceName="seq")
	private Long id;
	
	@Column(name="BPM")
	private int bpm;
	
	@Column(name="STARTVOLUME")
	private float startVolume;
	
	@Column(name="ENDVOLUME")
	private float endVolume;
	
	@Column(name="TAGS")
	@ElementCollection(targetClass = GenreTag.class)
	@Enumerated(EnumType.STRING)
	private Collection<GenreTag> tags;
	
	public AudioData(){}
	
	public AudioData(FileInputStream fileInputStream)
	{
		AudioDataHelper(fileInputStream);
	}
	
	public AudioData(FileInputStream fileInputStream, Collection<GenreTag> matchingTags)
	{
		AudioDataHelper(fileInputStream);
		tags.addAll(matchingTags);
	}
	
	//debug constructors
	public AudioData(int bpm){
		this.bpm = bpm;
		tags = new ArrayList<GenreTag>();
	}
	public AudioData(int bpm, Collection<GenreTag> matchingTags){
		this.bpm = bpm;
		tags.addAll(matchingTags);
	}
	
	public void AudioDataHelper(FileInputStream fileInputStream){
		try {
			MP3Decoder beatDecoder = new MP3Decoder(fileInputStream);
			MP3Decoder volumeDecoder = new MP3Decoder(fileInputStream);
			bpm = new BeatDetector().calculateBPM(beatDecoder);
			VolumeAnalyzer va = new VolumeAnalyzer(volumeDecoder);
			startVolume = va.getStartVolume();
			endVolume = va.getEndVolume();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tags = new ArrayList<GenreTag>();
	}
	
	public Collection<GenreTag> getTags(){
		return tags;
	}
	
	public boolean hasTag(GenreTag tag){
		return(tags.contains(tag));
	}

	public int getNumMatchingTags(Collection<GenreTag> matchingTags){
		int numMatchingTags = 0;
		for(GenreTag t : matchingTags){
			if(tags.contains(t)){
				numMatchingTags ++;
			}
		}
		return numMatchingTags;
	}
	
	public int getBPM()
	{
		return bpm;
	}
	
	public float getStartVolume() {
		return startVolume;
	}

	public float getEndVolume() {
		return endVolume;
	}
	
}
