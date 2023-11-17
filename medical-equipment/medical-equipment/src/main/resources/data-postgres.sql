INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Trebinje', 'BiH', 12, 12, 'Njegoseva', '5');
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Ruma', 'Srbija', 20, 12, 'Duciceva', '12');
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Nevesinje', 'BiH', 11, 12, 'Beogradska', '7');
	
	
INSERT INTO public.working_time_calendar(
	working_time_calendar_id)
	VALUES (default);
INSERT INTO public.working_time_calendar(
	working_time_calendar_id)
	VALUES (default);
INSERT INTO public.working_time_calendar(
	working_time_calendar_id)
	VALUES (default);
	
	
	
INSERT INTO public.company(
	company_id, average_grade, closing_hours, name, opening_hours, address_id, working_time_calendar_id)
	VALUES (default, 0, null, 'Prva kompanija', null, 1, 1);
INSERT INTO public.company(
	company_id, average_grade, closing_hours, name, opening_hours, address_id, working_time_calendar_id)
	VALUES (default, 5, null, 'Druga kompanija', null, 3, 2);
INSERT INTO public.company(
	company_id, average_grade, closing_hours, name, opening_hours, address_id, working_time_calendar_id)
	VALUES (default, 4, null, 'Treca kompanija', null, 2, 3);
	
	
	
	
INSERT INTO public."user"(
	user_id, city, country, email, employment, info_about_institution, logged_before, name, password, phone_number, surname, username, is_active)
	VALUES (default, 'Trebinje', 'BiH', 'kovacevic.ra15.2020@uns.ac.rs', 0, 'No info', false, 'Anja', 'anja', '012345678', 'Ducic', 'anja',false);
INSERT INTO public."user"(
	user_id, city, country, email, employment, info_about_institution, logged_before, name, password, phone_number, surname, username, is_active)
	VALUES (default, 'Ruma', 'Srbija', 'milicavujic2001@gmail.com', 0, 'No info', false, 'Milica', 'milica', '012345678', 'Vujic', 'milica', false);
INSERT INTO public."user"(
	user_id, city, country, email, employment, info_about_institution, logged_before, name, password, phone_number, surname, username, is_active)
	VALUES (default, 'Nevesinje', 'BiH', 'ducic.ra5.2020@uns.ac.rs', 0, 'No info', false, 'Ivana', 'ivana', '012345678', 'Kovacevic', 'ivana', false);



INSERT INTO public.registrated_user(
	category, penals, user_id)
	VALUES (0, 0, 1);
INSERT INTO public.company_admin(
	user_id, company_id)
	VALUES (2, 1);
	
	
INSERT INTO public.equipment(
	equipment_id, description, name, type)
	VALUES (default, 'Hirurske makaze', 'Oprema 1', 'Hirurska oprema');
INSERT INTO public.equipment(
	equipment_id, description, name, type)
	VALUES (default, 'Zavoj', 'Oprema 2', 'Zavoji');
	
INSERT INTO public.company_equipment(
	company_id, equipment_id)
	VALUES (1, 1);
INSERT INTO public.company_equipment(
	company_id, equipment_id)
	VALUES (1, 2);


INSERT INTO public.system_admin(
	user_id)
	VALUES (3);

