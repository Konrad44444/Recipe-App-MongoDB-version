package com.mongo.recipeapp.recipeappmongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.mongo.recipeapp.recipeappmongo.commands.NotesCommand;
import com.mongo.recipeapp.recipeappmongo.model.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setNote(source.getNote());
        return notes;
    }
}
