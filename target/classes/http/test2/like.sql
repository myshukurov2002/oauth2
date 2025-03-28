create or replace function function_like()
    returns trigger as
$$
begin
    if (tg_op = 'INSERT') then
        if NEW.type = 'LIKE' then
            update articles set likes = likes + 1
            where articles.id = NEW.article_id;

        elsif NEW.type = 'DISLIKE' then
            update articles set dislikes = dislikes + 1
            where articles.id = NEW.article_id;
        end if;

    elsif (tg_op = 'UPDATE') then

        if NEW.type = 'DISLIKE' and OLD.type = 'LIKE' then

            update articles set dislikes = dislikes + 1
            where articles.id = NEW.article_id;

                update articles set likes = likes - 1
            where articles.id = NEW.article_id;

        elsif NEW.type = 'LIKE' and OLD.type = 'DISLIKE' then
                update articles set dislikes = dislikes - 1
                where articles.id = NEW.article_id;

            update articles set likes = likes + 1
            where articles.id = NEW.article_id;
        end if;

    elsif (tg_op = 'DELETE') then

        if OLD.type = 'LIKE' then
            update articles set likes = likes - 1
            where articles.id = OLD.article_id;

        elsif OLD.type = 'DISLIKE' then
                update articles set dislikes = dislikes - 1
            where articles.id = OLD.article_id;
        end if;

    end if;

    return NEW;
end;
$$ language plpgsql;


create or replace trigger trigger_likes
    AFTER INSERT or UPDATE or DELETE
    on likes
    FOR EACH ROW
execute function function_like();



